package com.kjh.exam.demo.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.kjh.exam.demo.vo.Member;

@Mapper
public interface MemberRepository {

	@Insert("""
			INSERT INTO `member`
			SET regDate = NOW(),
			updateDate = NOW(),
			loginId = #{loginId},
			loginPw = #{loginPw},
			`name` = #{name},
			nickname = #{nickname},
			cellphoneNum = #{cellphoneNum},
			email = #{email}
			""")
	public void doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNum, String email);

	@Select("SELECT LAST_INSERT_ID()")
	public int getLastInssertId();

	@Select("""
			SELECT *
			FROM `member`
			WHERE id = #{id}
			""")
	public Member getMemberById(int id);

	@Select("""
			SELECT *
			FROM `member`
			WHERE loginId = #{loginId}
			""")
	public Member getMemberByLoginId(String loginId);

	@Select("""
			SELECT *
			FROM `member`
			WHERE name = #{name}
			AND email = #{email}
			""")
	public Member getMemberByNameAndEmail(String name, String email);

	@Update("""
			<script>
				UPDATE `member`
					<set>
						updateDate = NOW(),
						<if test="nickname != null">
							nickname = #{nickname},
						</if>
						<if test="cellphoneNum != null">
							cellphoneNum = #{cellphoneNum},
						</if>
						<if test="email != null">
							email = #{email}
						</if>
					</set>
					WHERE id = #{loginedMemberId}
				</script>
			""")
	public void doModify(int loginedMemberId, String nickname, String cellphoneNum, String email);

	@Update("""
			UPDATE `member`
				SET updateDate = NOW(),
				loginPw = #{loginPw}
				WHERE id = #{loginedMemberId}
			""")
	public void doPassWordModify(int loginedMemberId, String loginPw);

}
