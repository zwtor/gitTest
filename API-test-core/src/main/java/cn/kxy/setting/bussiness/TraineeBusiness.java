package cn.kxy.setting.bussiness;

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.TokenData;
import com.alibaba.fastjson.JSONPath;
import com.lazy.assured.utils.GetRequestTools;
import com.lazy.assured.utils.PostRequestTools;

public class TraineeBusiness {
	public static String enterpriseId = EnterpriseData.getEnterpriseId();

	public static String enterpriseUrl = EnterpriseDataUrl.getEnterpriseUrl();
	public static String token = TokenData.getMangerToken();
	public static String post_group_name = "RegGroup";

	public static String station_name = "tester";
	
	public static String addStationUrl() {
		return enterpriseUrl + "v2/" + enterpriseId + "/post_groups";
	}

	public static String addPostsUrl() {
		return enterpriseUrl + "v2/" + enterpriseId + "/posts";
	}
	public static String queryPostsListUrl = enterpriseUrl + "v2/"+enterpriseId+"/posts";
	
	
	public static String addUserToPostUrl = enterpriseUrl + "admin/post/addUsersToPost";

	public static String deleteUserUrl =enterpriseUrl + "admin/post/delUsersFromPost";
	
	public static String queryPostsList() {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token, queryPostsListUrl);
	}
	
	public static String addUsersToPost(String postId ) {
		return PostRequestTools.RequestFormDataByPost(token, "postId", postId, "id", postId, "userIds", UserBusiness.getUserId(),
				addUserToPostUrl);
	}

	//删除岗位的用户
	public static String deleteAppointPostUser(String postId) {
		return PostRequestTools.RequestFormDataByPost("postId", postId, "id", postId, "userIds",UserBusiness.getUserId(),deleteUserUrl);
	}
	
	// 新增岗位组
	public static String addStationGroup(String post_group_name) {
		return PostRequestTools
				.RequestBodyByPost(
						"{\r\n" + "  \"post_group_name\": \"" + post_group_name + "\", \r\n"
								+ "  \"parent_id\": \"\", \r\n" + "  \"access_token\": \"" + token + "\"\r\n" + "}",
						token, addStationUrl());
	}
	// 新增岗位组获取id
	public static String getStationGroupId(String post_group_name) {
		String res = addStationGroup(post_group_name);
		String post_group_id = (String) JSONPath.read(res, "$.post_group_id");
		return post_group_id;
	}

	// 新增岗位
	public static String addStation(String post_name, String parentId) {
		String res = PostRequestTools.RequestBodyByPost("{\"post_name\":\"" + post_name + "\",\"post_group_id\":\""
				+ parentId + "\",\"access_token\":\"" + token + "\"}", token, addPostsUrl());

		String post_id = (String) JSONPath.read(res, "$.post_id");
		return post_id;
	}
}
