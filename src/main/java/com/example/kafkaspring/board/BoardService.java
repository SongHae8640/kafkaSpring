package com.example.kafkaspring.board;

import com.example.kafkaspring.alarmSubscription.AlarmSubscriptionService;
import com.example.kafkaspring.board.dto.BoardDto;
import com.example.kafkaspring.pushAlarm.PushAlarm;
import com.example.kafkaspring.pushAlarm.PushAlarmService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final AlarmSubscriptionService alarmService;
    private final PushAlarmService pushAlarmService;

    public Board saveBoard(BoardDto boardDto) {
        Board board = boardRepository.save(boardDto.createBoard());
        System.out.println("saveBoard :: board = " + board);

        List<Integer> subscriberList = alarmService.findAlarmSubscriberByCategory(board.getCategory());
        List<PushAlarm> pushAlarmList = subscriberList.stream()
                .map(userId -> PushAlarm.builder()
                        .userId(userId)
                        .content(board.getCategory().getCategory() + "에 새로운 글이 등록되었습니다.")
                        .build())
                .collect(Collectors.toList());

        pushAlarmService.saveList(pushAlarmList);
        return board;
    }
}