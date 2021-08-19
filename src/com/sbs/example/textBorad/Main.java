package com.sbs.example.textBorad;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		List<Article> articles= new ArrayList<>();
		int lastArticleId = 0;
		while(true) {
			System.out.println("명령어 입력해주세요 :");
			String command = sc.nextLine().trim();
			
			if (command.equals("article write")) {
				String title;
				String body;
				int id = lastArticleId +1;
				System.out.println("제목:");
				title = sc.nextLine();
				System.out.println("내용:");
				body = sc.nextLine();
				
				Article article = new Article(id,title,body);
				articles.add(article);
				lastArticleId++;
				
				System.out.println(article);
			}else if(command.equals("article list")) {
				System.out.println("== 게시글 리스트 ==");
				if (articles.size() == 0) {
					System.out.println("게시물이 없습니다.");
					continue;
				}
				System.out.println("번호  /  제목");
				for (Article article : articles) {
					System.out.printf("%d  /  %s\n", article.id , article.title);
				}
			}
			else if (command.equals("systam exit")) {
				System.out.println("프로그램 종료");
				break;
			}
				
		}
	}
}
