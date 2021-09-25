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
        System.out.println("8. 아이템 날짜 역순으로 정렬 ( ls_date_desc )");
        System.out.println("9. 카테고리 조회( ls_cate )");
        System.out.println("10. 키워드 검색 [제목 & 내용] - 메뉴 입력시 키워드 함께 입력 ( find )");
        System.out.println("11. 키워드 검색 [카테고리] - 메뉴 입력시 키워드 함께 입력 ( find_cate )");
        System.out.println("12. 종료 ( exit )");
    }
    
    public static void prompt()
    {
    	System.out.print("\n메뉴 선택 > ");
    }
}
