package teamPro.bbangShuttle.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import teamPro.bbangShuttle.paging.Criteria;
import teamPro.bbangShuttle.paging.SearchCriteria;
import teamPro.bbangShuttle.vo.NoticeVO;

import java.util.List;
import java.util.Optional;

@Mapper
@Repository
public interface NoticeMapper {

  // ** SearchCriteria PageList
  List<NoticeVO> searchList(SearchCriteria cri);
  int searchTotalCount(SearchCriteria cri);

  // ** Criteria PageList
  List<NoticeVO> criList(Criteria cri);
  int criTotalCount();

  // ** selectList
  List<NoticeVO> selectList();

  // ** selectOne
  Optional<NoticeVO> selectOne(int noticeNo);

  // ** Insert
  int insert(NoticeVO vo);

  // ** Update
  int update(int noticeNo, NoticeVO vo);

  // ** Delete
  int delete(int noticeNo);

  // ** 조회수 증가
  int countUp(NoticeVO vo);

}
