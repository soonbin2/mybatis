package com.ohgiraffers.section01.xmlconfig;

import org.apache.ibatis.session.SqlSession;

import java.util.List;

import static com.ohgiraffers.section01.xmlconfig.Template.getSqlSession;

public class MenuService {

    private final MenuDAO menuDAO;

    public MenuService() {
        menuDAO = new MenuDAO();
    }
    public List<MenuDTO> selectAllMenu() {

        SqlSession sqlSession = getSqlSession();

        List<MenuDTO> menuList = menuDAO.selectAllMenu(sqlSession);

        sqlSession.close();
        return menuList;
    }

    public MenuDTO selectMenuByCode(int code) {
        SqlSession sqlSession = getSqlSession();
        MenuDTO menu = MenuDAO.selectMenuByCode(sqlSession, code);

        sqlSession.close();

        return menu;
    }

    public boolean registMenu(MenuDTO menuDTO) {

        SqlSession sqlSession = getSqlSession();

        int result = menuDAO.insertMenu(sqlSession,menuDTO);

        if(result > 0) {
            sqlSession.commit();
        }else{
            sqlSession.rollback();
        }

        sqlSession.close();
        return result > 0? true:false;
    }

    public boolean modifyMenu(MenuDTO menuDTO) {

        SqlSession sqlSession = getSqlSession();

        int result = menuDAO.modifyMenu(sqlSession,menuDTO);

        if(result > 0) {
            sqlSession.commit();
        }else{
            sqlSession.rollback();
        }
        return result > 0? true:false;
    }


    public boolean deleteMenuByCode(int code) {
        SqlSession sqlSession = getSqlSession();
        int result = menuDAO.deleteMenuByCode(sqlSession,code);
        if(result > 0) {
            sqlSession.commit();
        }else{
            sqlSession.rollback();
        }
        sqlSession.close();
        return result > 0? true:false;
    }
}
