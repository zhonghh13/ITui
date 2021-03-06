package cn.itui.webdevelop.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import cn.itui.webdevelop.dao.CollegeDao;
import cn.itui.webdevelop.model.College;

public class CollegeDaoImpl implements CollegeDao{
	private SqlSession sqlSession;

	/**
	 * 通过major的id获取College的logo,rank and local rank
	 * 返回一个hashmap,键为id,logo,rank,localRank
	 */
	public HashMap<String, Object> findLogoAndRankByMajorId(int id) {
		return sqlSession.selectOne("cn.itui.webdevelop.dao.CollegeDao.findLogoAndRankByMajorId", id);
	}

	/**
	 * 查找排名在某个范围内的College
	 * rank 为排名，取rank-4 ~ rank+4之间的
	 */
	public List<College> findCollegeInRank(int rank, int collegeId) {
		HashMap<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("curRank", rank);
		parameterMap.put("collegeId", collegeId);
		return sqlSession.selectList("cn.itui.webdevelop.dao.CollegeDao.findCollegeInRank", parameterMap);
	}

	public SqlSession getSqlSession() {
		return sqlSession;
	}

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	/**
	 * 根据condition搜索学校
	 * hashmap的key有id, name, logo, is211, is985, is34, rank, loaclRank, city
	 */
	public List<HashMap<String, Object>> searchCollegesByName(String condition, String area, String is211, String is34, String is985, int from, int limit) {
		HashMap<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("area", area);
		parameter.put("condition", condition);
		parameter.put("is211", is211);
		parameter.put("is34", is34);
		parameter.put("is985", is985);
		parameter.put("from", from);
		parameter.put("limit", limit);
		return sqlSession.selectList("cn.itui.webdevelop.dao.CollegeDao.searchCollegesByName", parameter);
	}

	/**
	 * 根据college的id搜索对应的school
	 * 返回结果为所有school名称
	 */
	public List<String> findSchoolsByCollegeId(int id) {
		return sqlSession.selectList("cn.itui.webdevelop.dao.CollegeDao.findSchoolsByCollegeId", id);
	}

	/**
	 * 根据学校id和school来搜索该school下的所有专业
	 * 返回结果key包括id, name
	 */
	public List<HashMap<String, Object>> findMajorsBySchool(int collegeId,
			String school) {
		HashMap<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("collegeId", collegeId);
		parameterMap.put("school", school);
		return sqlSession.selectList("cn.itui.webdevelop.dao.CollegeDao.findMajorsBySchool", parameterMap);
	}

	public HashMap<String, Object> getCollegeInfo(int collegeId) {
		return sqlSession.selectOne("cn.itui.webdevelop.dao.CollegeDao.getCollegeInfo", collegeId);
	}

	public int getTotal(String condition, String area, String is211,
			String is34, String is985) {
		HashMap<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("area", area);
		parameter.put("condition", condition);
		parameter.put("is211", is211);
		parameter.put("is34", is34);
		parameter.put("is985", is985);
		return sqlSession.selectOne("cn.itui.webdevelop.dao.CollegeDao.getTotal", parameter);
	}
	
	/*
	 * add
	 * */

	public List<HashMap<String, Object>> getCollegeRankInfos() {
		return sqlSession.selectList("cn.itui.webdevelop.dao.CollegeDao.getCollegeRankInfos");
	}
	
	public List<HashMap<String, Object>> getCollegeLocalRankInfos(int collegeId) {
		return sqlSession.selectList("cn.itui.webdevelop.dao.CollegeDao.getCollegeLocalRankInfos",collegeId);
	}

	public int getCollegeIdByName(String collegeName) {
		return sqlSession.selectOne("cn.itui.webdevelop.dao.CollegeDao.getCollegeIdByName",collegeName);
	}

	
	
}
