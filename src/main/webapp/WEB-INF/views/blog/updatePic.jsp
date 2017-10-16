<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="${path}/js/updateProfile.js"></script>
<div class="updateWraper" style="display: none; position: fixed; width: 100%; height: 100vh; background-color: rgba(0, 0, 0, 0.6)">
	<div class="helper" style="#position: absolute; #top: 50%;  display: table-cell; height: 50%; vertical-align: middle;">
		<div class="updateContainer" style="#position: relative; #top: -50%; width: 785px; height:50%; margin: 0 auto;">
			<div class="title">
				<div style="float: right"><div id="updatePicRemoveBtn"></div></div>
				<div><h3>Update Profile Picture</h3></div>
			</div>
			<div class="updateMain">
				<div style="border-bottom: 1px solid #e9ebee; /* display: flex; padding: 4px; */">
					<form method="post" action="/only/updateProfileImage" enctype="multipart/form-data">
					<div class= "updateMainBox" style="width: 95%; position: relative; flex: 1 1 0%;">
						
						<div>
							<div style="bottom: 0; left: 0; width: 50%; position: absolute; right: 0; top: 0;">
								<div class="imageUpdateLabel" style="color: #ccc; text-shadow: none;">
									<div> + Upload Photo</div>
								</div>
							</div>
							<input type="file" name="file" title="Choose a file to upload" accept="image/*" style="width: 100%; height: 100%; " id="thumbProfileImg">
						</div>
						
						<div id="aaaaa" style="bottom: 0; left: 50%; position: absolute; right: 0; top: 0;;">
							<div id="uploadedImage" style="width: 50%; height: 100%;"></div>				
						</div>
					</div>
					</form>
					<hr>
					<div style="margin-top: 20px; margin-left: 8px; argin-right: 8px; display: block;">
						<div style="overflow: hidden;">
							<div style="color: #90949c; overflow: hidden; text-overflow: ellipsis; white-space: nowrap;">
								Choose from uploaded photos
							</div>
							<div class="photoDisplay">
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>