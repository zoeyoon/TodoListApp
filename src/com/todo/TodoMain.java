package com.todo;

import java.util.Scanner;

import com.todo.dao.TodoList;
import com.todo.menu.Menu;
import com.todo.service.TodoUtil;

public class TodoMain {
	
	public static void start() {
	
		Scanner sc = new Scanner(System.in);
		TodoList l = new TodoList();
		boolean isList = false;
		boolean quit = false;
		
		TodoUtil.loadList(l, "todoList.txt");
		
		Menu.displaymenu();
		do {
			Menu.prompt();
			isList = false;
			String input = sc.nextLine();
			String[] arr = input.split(" ");
			
			String choice = arr[0];
			switch (choice) {

			case "add":
				TodoUtil.createItem(l);
				break;
			
			case "del":
				TodoUtil.deleteItem(l);
				break;
				
			case "edit":
				TodoUtil.updateItem(l);
				break;
				
			case "ls":
				TodoUtil.listAll(l);
				break;

			case "ls_name_asc":
				l.sortByName();
				System.out.println("\n========= 정렬-이름(오름차순) ==========");
				isList = true;
				break;

			case "ls_name_desc":
				l.sortByName();
				l.reverseList();
				System.out.println("\n========= 정렬-이름(내림차순) ==========");
				isList = true;
				break;
				
			case "ls_date":
				l.sortByDate();
				System.out.println("\n========= 정렬-날짜 순 ==========");
				isList = true;
				break;
				
			case "ls_date_desc":
				l.sortByDate();
				l.reverseList();
				System.out.println("\n========= 정렬-날짜 순 ==========");
				isList = true;
				break;
			
			case "ls_cate":
				TodoUtil.listCate(l);
				break;
				
			case "find":
				try {
					TodoUtil.findItem(l, arr[1]);
					break;
				}
				catch (IndexOutOfBoundsException e) {
					System.out.println("키워드 입력값 없음!");
					break;
				}
				
				
			case "find_cate":
				try {
					TodoUtil.findCateItem(l, arr[1]);
					break;
				}
				catch (IndexOutOfBoundsException e) {
					System.out.println("키워드 입력값 없음!");
					break;
				}
				
				
			case "help":
				Menu.displaymenu();
				System.out.println();
				break;
				
			case "exit":
				quit = true;
				break;
				
			default:
				System.out.println("\n제공하지 않는 기능입니다. 다시 입력해주세요. - 도움말은 \"help\"를 입력");
				break;
			}
			
			if(isList) l.listAll();
		} while (!quit);
		TodoUtil.saveList(l, "todoList.txt");
	}
}
