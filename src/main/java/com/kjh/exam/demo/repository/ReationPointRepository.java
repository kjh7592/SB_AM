package com.kjh.exam.demo.repository;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.kjh.exam.demo.vo.ReactionPoint;

@Mapper
public interface ReationPointRepository {

	
	@Select("""
			SELECT IFNULL(SUM(`point`), 0) AS sumReactionPoint,
					IFNULL(SUM(IF(`point` > 0, `point`, 0)), 0) AS goodReactionPoint,
					IFNULL(SUM(IF(`point` < 0, `point`, 0)), 0) AS badReactionPoint
				FROM reactionPoint
				WHERE relTypeCode = #{relTypeCode}
				AND memberId = #{loginedMemberId}
				AND relId = #{id};
			
			""")
	ReactionPoint getReactionPoint(int loginedMemberId, String relTypeCode, int id);

	@Insert("""
			INSERT INTO reactionPoint
				SET regDate = NOW(),
					updateDate = NOW(),
					memberId = #{loginedMemberId},
					relTypeCode = #{relTypeCode},
					relId = #{id},
					`point` = #{point};
			""")
	void doReactionPoint(int loginedMemberId, int id, String relTypeCode, int point);

	@Delete("""
			DELETE FROM reactionPoint
				WHERE relTypeCode = #{relTypeCode}
				AND memberId = #{loginedMemberId}
				AND relId = #{id};
			""")
	void delReactionPoint(int loginedMemberId, String relTypeCode, int id);
}
