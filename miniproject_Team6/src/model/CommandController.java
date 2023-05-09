package model;

import java.util.Arrays;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter

public enum CommandController { //열거형 데이터 타입, 커맨드 읽어온 후 분리
    FIRST("1"), //상수명으로 접근
    SECOND("2"),
    THIRD("3"),
    QUIT("Q");

    private static final String INVALID_COMMAND_MSG = "올바르지 않은 입력 값입니다.";
    private final String command; 

    public static CommandController from(String command){
        return Arrays.stream(values()) //values()? 커맨드를 분리해서 return
        		//filter command분류
                .filter(commandController -> commandController.command.equals(command))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_COMMAND_MSG));
    }
    public boolean isNotQuit() { 
    	return this != QUIT; }


}
