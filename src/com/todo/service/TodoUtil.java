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
		
		String title, desc, category, due_date;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n"
				+ "========== 아이템 추가 ==========\n"
				+ "제목을 입력하세요 > ");
		
		title = sc.nextLine();
		if (list.isDuplicate(title)) {
			System.out.println("======== 중복된 제목입니다 ========");
			return;
		}
		
		System.out.print("카테고리를 입력하세요 > ");
		category = sc.nextLine();
		
		System.out.print("설명 내용을 입력하세요 > ");
		desc = sc.nextLine();
		
		System.out.print("마감 일자를 입력하세요 > ");
		due_date = sc.nextLine();
		
		TodoItem t = new TodoItem(title, desc, category, due_date);
		list.addItem(t);
		System.out.println("========== 추가 성공! ==========");
	}

	public static void deleteItem(TodoList l) {
		Scanner sc = new Scanner(System.in);
		try {
			System.out.print("\n"
				+ "========== 아이템 삭제 ==========\n"
				+ "삭제할 아이템의 번호를 입력하세요 > ");
			int num = sc.nextInt();
			TodoItem item = l.getList().get(num-1);
			System.out.printf("%2d. [%s] %s - %s - %s - %s\n", num, item.getCategory(), item.getTitle(), item.getDesc(), item.getDue_date(), item.getCurrent_date());
			sc.nextLine();
			System.out.print("삭제할까요? (y/n) > ");
			String answer = sc.nextLine().trim();
			
			if (answer.equals("y")) {
				l.deleteItem(item);
				System.out.println("========== 삭제 완료! ==========");
			}
			else {
				System.out.println("========== 삭제 취소! ==========");
			}
		}
		
		catch (IndexOutOfBoundsException e) {
			System.out.println("======== 없는 항목입니다 =========");
		}
		
	}


	public static void updateItem(TodoList l) {
		Scanner sc = new Scanner(System.in);
		try {
			System.out.print("\n"
				+ "========== 아이템 수정 ==========\n"
				+ "수정할 아이템의 번호를 입력하세요 > ");
			int num = sc.nextInt();
			
			TodoItem item = l.getList().get(num-1);
			System.out.printf("%2d. [%s] %s - %s - %s - %s\n", num, item.getCategory(), item.getTitle(), item.getDesc(), item.getDue_date(), item.getCurrent_date());
			sc.nextLine();
			
			System.out.print("새로운 제목을 입력하세요 > ");
			
			String new_title = sc.nextLine().trim();
			if (l.isDuplicate(new_title)) {
				System.out.println("======== 중복된 제목입니다 ========");
				return;
			}
			
			System.out.print("새로운 카테고리를 입력하세요 > ");
			String new_category = sc.nextLine().trim();
			
			System.out.print("새로운 설명 내용을 입력하세요 > ");
			String new_description = sc.nextLine().trim();
			
			System.out.print("새로운 마감일자를 입력하세요 > ");
			String new_due_date = sc.nextLine().trim();
			
			l.deleteItem(item);
			TodoItem t = new TodoItem(new_title, new_description, new_category, new_due_date);
			l.addItem(t);
			System.out.println("========== 수정 완료! ==========");
		}
		
		catch (IndexOutOfBoundsException e) {
			System.out.println("======== 없는 항목입니다 =========");
		}

	}
	
	public static void findItem(TodoList l, String word) {
		System.out.println("======== 키워드 검색 결과 [제목 & 설명] =========");
		int count=0;
		for (TodoItem item : l.getList()) {
			if (item.getTitle().contains(word) || item.getDesc().contains(word)) {
				System.out.printf("%2d. [%s] %s - %s - %s - %s\n", l.getList().indexOf(item)+1, item.getCategory(), item.getTitle(), item.getDesc(), item.getDue_date(), item.getCurrent_date());
				count++;
			}
		}
		System.out.println("\n총 " + count + "개의 항목을 찾았습니다.");
		System.out.println("============= end of result ==============");
	}
	
	public static void findCateItem(TodoList l, String word) {
		System.out.println("======== 키워드 검색 결과 [카테고리] =========");
		int count=0;
		for (TodoItem item : l.getList()) {
			if (item.getCategory().contains(word)) {
				System.out.printf("%2d. [%s] %s - %s - %s - %s\n", l.getList().indexOf(item)+1, item.getCategory(), item.getTitle(), item.getDesc(), item.getDue_date(), item.getCurrent_date());
				count++;
			}
		}
		System.out.println("\n총 " + count + "개의 항목을 찾았습니다.");
		System.out.println("============ end of result =============");
	}
	
	public static void listCate(TodoList l) {
		Set<String> s= new HashSet<String>();
		
		for (TodoItem item : l.getList()) {
			s.add(item.getCategory());
		}
		
		System.out.println("======== 카테고리 목록 =========");
		List<String> lc = new ArrayList<String>();
		lc.addAll(s);
		
		for(int i=0; i<lc.size(); i++) {
			System.out.print(lc.get(i));
			if (i < lc.size()-1) System.out.print(" / ");
		}
		System.out.println("\n총 " + s.size() + "개의 카테고리가 등록되어 있습니다.");
	}
	
	public static void listAll(TodoList l) {
		System.out.println("\n"+"[아이템 목록 : " + l.getList().size() + "개]");
		int i=0;
		for (TodoItem item : l.getList()) {
			System.out.printf("%2d. [%s] %s - %s - %s - %s\n", i+1, item.getCategory(), item.getTitle(), item.getDesc(), item.getDue_date(), item.getCurrent_date());
			i++;
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
				
				String category = s.nextToken();
				String title = s.nextToken();
				String desc = s.nextToken();
				String due_date = s.nextToken();
				String date = s.nextToken();
				
				TodoItem t = new TodoItem(title, desc, category, due_date);
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
