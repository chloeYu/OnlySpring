<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="contentArea">
	<div class="blogTopSection">
		<div id="profileCover">
			<div class="  ">
				<a class="coverWrap"> <img class="photo"
					src="${path }/only/img_timeline/${owner.cover_image}" >
				</a>
				<c:if test="${owner.userid eq member.userid }">
					<div class="coverImageUpdate">
						<div class="editCoverBtn" data-type="cover"></div>
					</div>
				</c:if>
			</div>
		</div>
		<div class="blogProfileName">
			<h1 class="h1ProfileName">${owner.username }</h1>
		</div>
	</div>
	<div class="profileHeadline">
		<div class="profileNav">
			<ul stype="border-left: 1px solid #e9eaed;">
				<li><a href='/only/blog/${owner.userid }'>Timeline</a></li>
				<li><a href='/only/blog/${owner.userid }/about'>About</a></li>
				<li><a href="/only/blog/${owner.userid }/friendList">
						<!-- onclick="getFriendList()" -->Friends
				</a></li>
				<li><a href="/only/blog/${owner.userid }/photos">Photo</a></li>
			</ul>
		</div>
		<div class="profileThumb">
			<div class="photoContainer">
				<a class="profilePicThumb"> <img
					src="${path}/only/img_timeline/${owner.profile_image }"> <c:if
						test="${owner.userid eq member.userid }">
						<div class="thumbImageUpdate">
							<div class="editCoverBtnHover">
								<table>
									<tr>
										<td><div class="editCoverBtn" data-type="thumb"></div></td>
										<td><span>Update Profile Picture</span></td>
								</table>
							</div>
						</div>
					</c:if>
				</a>

			</div>
		</div>
	</div>

</div>