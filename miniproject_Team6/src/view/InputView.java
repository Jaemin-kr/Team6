package view;

import java.util.ArrayList;
import java.util.Scanner;

import controller.Controller;
import model.dto.CustomerDTO;
import model.dto.CommandDto;

public class InputView {

	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		Controller controller = new Controller();
		controller.run(); //runnable interface
	}

	private static final String WELCOME_MSG = "\n 번호를 선택해주세요 \n" + "1. 회원주문\n" + "2. 비회원주문\n" + "3. 회원가입\n" + "Q. 종료\n";

	private static final String FIRST_MSG = "회원정보(이름, 전화번호)를 입력해주세요\n" + "예시) 고재민,010-4512-6065";
	private static final String SECOND_MSG = "주문하실 메뉴의 이름을 입력하세요.";

	private static final String THIRD_MSG = "회원가입 정보를 입력해주세요.\n" + "이름, 번호, 생년월일, 성별(M or F)\n"
			+ "예시) 고재민,010-4512-6065,980731,M";
	private static final String QUIT_MSG = "이용해주셔서 감사합니다. 안녕히 가세요.";
	
	public CommandDto readCmd() {
		System.out.println(WELCOME_MSG);
		String cmd = sc.next().toUpperCase();
		return new CommandDto(cmd);
	}
//	public CommandDto nextReadCmd() {
//		System.out.println();
//		String cmd = sc.next().toUpperCase();
//		return new CommandDto(cmd);
//	}

	public String firstMsg() { //회원 주문
		System.out.println(FIRST_MSG);
		String msg = sc.next();
		return msg;
	}

	public String secondMsg() { //비회원주문
		System.out.println(SECOND_MSG);
		//메뉴판 불러오기
		Controller.getAllMenu();
		String msg = sc.next();
		return msg;
	}
	
	public String thirdMsg() { //회원가입
		System.out.println(THIRD_MSG);
		String msg = sc.next();
		return msg;
	}	
	
}

