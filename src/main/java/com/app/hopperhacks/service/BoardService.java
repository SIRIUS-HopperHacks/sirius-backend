package com.app.hopperhacks.service;

import com.app.hopperhacks.config.Pagination;
import com.app.hopperhacks.domain.Board;
import com.app.hopperhacks.domain.User;
import com.app.hopperhacks.repository.BoardRepository;
import com.app.hopperhacks.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class BoardService {

    private BoardRepository boardRepository;
    private UserRepository userRepository;

    @Autowired
    private void setBoardRepository(BoardRepository boardRepository){this.boardRepository = boardRepository;}
    @Autowired
    private void setUserRepository(UserRepository userRepository){this.userRepository= userRepository;}

    @Autowired
    public BoardService(){System.out.println("###LOG : BoardService() 생성");}

    @Transactional
    public List<Board> board_detail(Long board_code){
        List<Board> list = new ArrayList<>();
        Board board = boardRepository.findByBoardCode(board_code);

        if(board != null){
            boardRepository.saveAndFlush(board);
            list.add(board);
        }
        return list;
    }

    public int board_write(Board board){
        User user = null; //TODO 현재 로그인한 사용자 정보

        user = userRepository.findByUserCode(user.getUserCode());
        if (user != null){
            board.setUser(user);
            board = boardRepository.saveAndFlush(board);
        }
        return 1;
    }

    //전체조회
    public List<Board> board_list(){return boardRepository.findAll();}

    //페이징 전체조회
    public List<Board> board_list(Integer page, Model model){
        if(page == null) page = 1;
        if(page < 1) page = 1;

        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attrs.getRequest().getSession();

        //BOARDPAGES : 한 페이지당 몇개의 페이지 표시할지
        //PAGEROWS : 한 페이지에 몇개 글 리스트할지
        Integer boardPages = (Integer)session.getAttribute("boardPages");
        if(boardPages == null) boardPages = Pagination.BOARD_PAGES; //세션에 없으면 default(10) 값으로 동작
        Integer pageRows = (Integer)session.getAttribute("pageRows");
        if(pageRows == null) pageRows = Pagination.PAGE_ROWS; //세션에 없으면 default(10) 값으로 동작
        session.setAttribute("page",page); //현재 페이지 번호를 session에 저장

        Page<Board> pageBoard = boardRepository.findAll(PageRequest.of(page-1, pageRows, Sort.by(Sort.Order.desc("board_code"))));

        long cnt = pageBoard.getTotalElements();
        int totalPage = pageBoard.getTotalPages();

        if(page > totalPage) page = totalPage;

        //페이징에 표시할 시작페이지 와 마지막페이지 계산
        int startPage = ((int)((page-1) / boardPages) * boardPages) + 1;
        int endPage = startPage + boardPages - 1;
        if(endPage >= totalPage) endPage = totalPage;

        model.addAttribute("cnt", cnt); //전체 글 개수
        model.addAttribute("page", page); //현재 페이지
        model.addAttribute("totalPage", totalPage); //총 페이지 수
        model.addAttribute("pageRows",pageRows); //한 페이지에 표시할 글 개수

        //페이징
        model.addAttribute("url", attrs.getRequest().getRequestURI());
        model.addAttribute("boardPages", boardPages); // 페이징에 표시할 숫자 개수
        model.addAttribute("startPage", startPage); //페이징에 표시할 시작페이지
        model.addAttribute("endPage", endPage); //페이징에 표시할 마지막 페이지

        //해당 페이지의 글 목록 조회
        List<Board> list = pageBoard.getContent();
        model.addAttribute("board_list", list);

        return list;
    }

    //단일 글 조회
    public List<Board> selectByBoard_code(Long board_code){
        List<Board> list = new ArrayList<>();

        Board board = boardRepository.findByBoardCode(board_code);
        if(board != null){list.add(board);}
        return list;
    }

    public int board_update(Board board){
        int result = 0;

        //update 할 글 조회
        Board prev_board = boardRepository.findByBoardCode(board.getBoardCode());
        if(prev_board != null){
            prev_board.setBoardTitle(board.getBoardTitle());
            prev_board.setBoardContent(board.getBoardContent());
            boardRepository.save(prev_board);
            return 1;
        }
        return result;
    }

    public int board_delete(Long board_code){
        int result = 0;
        Board board = boardRepository.findByBoardCode(board_code);
        if(board!=null){
            boardRepository.delete(board);
            return 1;
        }
        return 0;
    }


}
