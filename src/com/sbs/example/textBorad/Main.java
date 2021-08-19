package com.sbs.example.textBorad;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		List<Article> articles= new ArrayList<>();
		while(true) {
			System.out.println("명령어 입력해주세요 :");
			String command = sc.nextLine().trim();
			if (command.equals("systam exit")) {
				System.out.println("프로그램 종료");
				break;
			}else if (command.equals("")) {
				
			}
				
		}
	}
}
