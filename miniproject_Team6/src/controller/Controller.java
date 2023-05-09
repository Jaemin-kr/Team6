package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.sql.SQLException;

import model.CommandController;
import model.dto.CommandDto;
import view.InputView;
import model.CustomerDAO;
import model.dto.CustomerDTO;
import view.OutputView;
import model.MenuDAO;
import view.OutputView;

public class Controller {

	private final Map<CommandController, Runnable> service = new HashMap<>();
	private final InputView inputView = new InputView();
	private final OutputView outputView = new OutputView();

	public Controller() {
		service.put(CommandController.FIRST, this::firstView); // 1번 선택
		service.put(CommandController.SECOND, this::secondView);// 2번 선택
		service.put(CommandController.THIRD, this::thridView);// 3번 선택
	}

	// 회원가입시 회원 정보 저장하기
	public static boolean addCustomer(ArrayList<String> info) {
		boolean result = false;
		try {
			result = CustomerDAO.createCustomer(info);
			OutputView.print("회원가입이 완료되었습니다.");
		} catch (SQLException s) {
			s.printStackTrace();
			OutputView.printException("모든 프로젝트 저장시 에러 발생");
		}
		return result;
	}
	
	
	public static boolean login(String userInfo){
		boolean result = false;
		try {
			
			ArrayList<String> info = infomSplit(userInfo);
			result = CustomerDAO.checkCustomer(new CustomerDTO(info.get(0),info.get(1)));
			
			if(result) {
				OutputView.print("확인되었습니다.");
				return true;
			}else {
				OutputView.printException("해당 회원 정보가 존재하지 않습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static void grade(String userInfo) {
		ArrayList<String> info = infomSplit(userInfo);
		try {
			
			OutputView.allView(CustomerDAO.getGname(info.get(0)));
		} catch (Exception e) {
			e.printStackTrace();
			OutputView.printException(info.get(0));
		}
	}
	public void run() { //main실행
		CommandController commandController = readCommand();
		while (commandController.isNotQuit()) {
			try {
				service.get(commandController).run();
			} catch (IllegalArgumentException e) {
				outputView.printException(e.getMessage());
			} finally {
				commandController = readCommand();
			}
// 종료메세지는 static 메소드로 출력
		}
	}

	public CommandController readCommand() { //커맨드 읽어서 반환
//		CommandDto commandDto = Retry.execute(inputView::readCmd);
		return CommandController.from(commandDto.getCommand());
	}
	
//	public CommandController nextReadcommand() {
//		CommandDto commandDto = Retry.execute(inputView::readNextCmd);
//		return CommandController.from(commandDto.getCommand());
//	}

	public void firstView() {
		String userInfo = inputView.firstMsg();
		//회원 정보 확인
		Controller.login(userInfo);
		//메뉴판 불러오기
		String menuName = inputView.secondMsg();
		//주문 메뉴의 가격 정보 가져오기
		Controller.setOrder(menuName);
		//등급 정보 가져오기
		Controller.grade(userInfo);
	}

	public void secondView() { //비회원 주문
		//메뉴판 불러오기
		String menuName = inputView.secondMsg();
		//주문 메뉴의 가격 정보 가져오기
		Controller.setOrder(menuName);
	}

	public void thridView() { // 회원가입
		CustomerDTO cd = new CustomerDTO();
		ArrayList<String> info = infomSplit(inputView.thirdMsg());
		Controller.addCustomer(info);
	}
	
	public static ArrayList<String> infomSplit(String msg){
		String [] information = msg.split(",");		
		return new ArrayList<>(Arrays.asList(information));
	}

	public static boolean getAllMenu() {
		try {

			 OutputView.getMenuListView(MenuDAO.getAllMenu());

		} catch (SQLException s) {
			s.printStackTrace();
			OutputView.printException("메뉴판 생성에 실패하였습니다.");
			return false;
		}
		return true;
	}

	public static boolean setOrder(String menuName) {
	
		try {
			OutputView.OrderView(MenuDAO.getPrice(menuName));
		} catch (SQLException e) {
			e.printStackTrace();
			OutputView.printException(menuName + "의 가격 정보를 불러오지 못했습니다.");
			return false;
		}
		return true;
	}

}
