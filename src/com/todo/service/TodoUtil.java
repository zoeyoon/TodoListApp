package com.todo.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.Writer;

import java.util.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void createItem(TodoList list) {
		
		String title, desc;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n"
				+ "========== 아이템 추가 ==========\n"
				+ "아이템의 제목을 입력하세요 > ");
		
		title = sc.nextLine();
		if (list.isDuplicate(title)) {
			System.out.println("======== 중복된 제목입니다 ========");
			return;
		}
		
		System.out.print("아이템 설명 내용을 입력하세요 > ");
		desc = sc.nextLine();
		
		TodoItem t = new TodoItem(title, desc);
		list.addItem(t);
		System.out.println("========== 추가 성공! ==========");
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n"
				+ "========== 아이템 삭제 ==========\n"
				+ "삭제할 아이템의 제목을 입력하세요 > ");
		
		String title = sc.nextLine();
		
		for (TodoItem item : l.getList()) {
			if (title.equals(item.getTitle())) {
				l.deleteItem(item);
				System.out.println("========== 삭제 완료! ==========");
				break;
			}
		}
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n"
				+ "========== 아이템 수정 ==========\n"
				+ "수정할 아이템의 제목을 입력하세요 > ");
		String title = sc.nextLine().trim();
		if (!l.isDuplicate(title)) {
			System.out.println("======== 없는 항목입니다 =========");
			return;
		}
		System.out.print("해당 아이템의 새로운 이름을 입력하세요 > ");
		String new_title = sc.nextLine().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("======== 중복된 제목입니다 ========");
			return;
		}
		
		System.out.print("해당 아이템의 새로운 설명 내용을 입력하세요 > ");
		String new_description = sc.nextLine().trim();
		for (TodoItem item : l.getList()) {
			if (item.getTitle().equals(title)) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_title, new_description);
				l.addItem(t);
				System.out.println("========== 수정 완료! ==========");
			}
		}

	}

	public static void listAll(TodoList l) {
		System.out.println("\n"+"[아이템 목록]");
		for (TodoItem item : l.getList()) {
			System.out.println("[" + item.getTitle() + "] " + item.getDesc() + " - " + item.getCurrent_date());
		}
	}
	
	public static void saveList(TodoList l, String filename) {
		try {
			Writer w = new FileWriter(filename);
			
			for(TodoItem item: l.getList()) {
				w.write(item.toSaveString());
			}
			w.close();
			
			System.out.println("\n파일 업데이트 완료!");
		}
		
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void loadList(TodoList l, String filename) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			
			String line;
			int count=0;
			
			while((line = br.readLine())!= null) {
				StringTokenizer s = new StringTokenizer(line, "##");
				
				String title = s.nextToken();
				String desc = s.nextToken();
				String date = s.nextToken();
				
				TodoItem t = new TodoItem(title, desc);
				t.setCurrent_date(date);
				
				l.addItem(t);
				count++;
			}
			br.close();
			
			System.out.println(count + "개의 데이터를 읽었습니다.");
		}
		catch (FileNotFoundException e) {
			System.out.println(filename + "파일을 찾을 수 없습니다.");
		}
		
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
