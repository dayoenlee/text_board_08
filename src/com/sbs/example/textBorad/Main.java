package com.sbs.example.textBorad;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
				// Insert적용
				Connection conn = null;
				PreparedStatement pstat = null;
				try {
					Class.forName("com.mysql.jdbc.Driver");
					String url = "jdbc:mysql://127.0.0.1:3306/text_board?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true&zeroDateTimeNehavior=convertToNull";

					conn = DriverManager.getConnection(url, "root", "");
					System.out.println("연결 성공!");
					
					
					String sql = "INSERT INTO article";
					sql += " SET regDate = NOW()";
					sql += ", updateDate = NOW()";
					sql += ", title =\'"+ title +"\'";
					sql += ",`body` =\'"+ body +"\';";
					
					pstat = conn.prepareStatement(sql);
					int affectedRows = pstat.executeUpdate();
					
					System.out.println("affectedRows : " + affectedRows);
					
					
				} catch (ClassNotFoundException e) {
					System.out.println("드라이버 로딩 실패");
				} catch (SQLException e) {
					System.out.println("에러: " + e);
				} finally {
					try {
						if (conn != null && !conn.isClosed()) {
							conn.close();
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
					try {
						if (pstat != null && !conn.isClosed()) {
							conn.close();
						}
					}catch (SQLException e) {
						e.printStackTrace();
					}
				}
				
				
				lastArticleId++;
				System.out.println(article);
				//Insert 완료
				
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
