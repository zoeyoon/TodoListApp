package com.todo.menu;
public class Menu {

    public static void displaymenu()
    {
        System.out.println("\n[메뉴 목록]");
        System.out.println("1. 아이템 추가 ( add )");
        System.out.println("2. 아이템 삭제 ( del )");
        System.out.println("3. 아이템 수정 ( edit )");
        System.out.println("4. 아이템 리스트 출력 ( ls )");
        System.out.println("5. 아이템 이름 오름차순으로 정렬 ( ls_name_asc )");
        System.out.println("6. 아이템 이름 내림차순으로 정렬 ( ls_name_desc )");
        System.out.println("7. 아이템 날짜 순으로 정렬 ( ls_date )");
        System.out.println("8. 종료 ( exit )");
    }
    
    public static void prompt()
    {
    	System.out.print("\n메뉴 선택 > ");
    }
}
