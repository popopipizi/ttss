<template>
	<el-main class="bg edit_wrap">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">

							<el-col v-if="user_group === '管理员' || $check_field('get','seat_number') || $check_field('add','seat_number') || $check_field('set','seat_number')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="座位号码" prop="seat_number">
												<el-input id="seat_number" v-model="form['seat_number']" placeholder="请输入座位号码"
							  v-if="user_group === '管理员' || (form['seat_information_id'] && $check_field('set','seat_number')) || (!form['seat_information_id'] && $check_field('add','seat_number'))" :disabled="disabledObj['seat_number_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','seat_number')">{{form['seat_number']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','number_of_seats') || $check_field('add','number_of_seats') || $check_field('set','number_of_seats')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="座位人数" prop="number_of_seats">
												<el-input id="number_of_seats" v-model="form['number_of_seats']" placeholder="请输入座位人数"
							  v-if="user_group === '管理员' || (form['seat_information_id'] && $check_field('set','number_of_seats')) || (!form['seat_information_id'] && $check_field('add','number_of_seats'))" :disabled="disabledObj['number_of_seats_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','number_of_seats')">{{form['number_of_seats']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','seat_status') || $check_field('add','seat_status') || $check_field('set','seat_status')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="座位状态" prop="seat_status">
								<el-select id="seat_status" v-model="form['seat_status']"
						v-if="user_group === '管理员' || (form['seat_information_id'] && $check_field('set','seat_status')) || (!form['seat_information_id'] && $check_field('add','seat_status'))">
						<el-option v-for="o in list_seat_status" :key="o" :label="o" :value="o">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','seat_status')">{{form['seat_status']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','seat_image') || $check_field('add','seat_image') || $check_field('set','seat_image')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="座位图片" prop="seat_image">
								<el-upload :disabled="disabledObj['seat_image_isDisabled']" id="seat_image" class="avatar-uploader" drag
						accept="image/gif, image/jpeg, image/png, image/jpg" action="" :http-request="upload_seat_image"
						:show-file-list="false" v-if="user_group === '管理员' || (form['seat_information_id'] && $check_field('set','seat_image')) || (!form['seat_information_id'] && $check_field('add','seat_image'))">
						<img v-if="form['seat_image']" :src="$fullUrl(form['seat_image'])" class="avatar">
						<i v-else class="el-icon-plus avatar-uploader-icon"></i>
					</el-upload>
					<el-image v-else-if="$check_field('get','seat_image')" style="width: 100px; height: 100px"
						:src="$fullUrl(form['seat_image'])" :preview-src-list="[$fullUrl(form['seat_image'])]">
						<div slot="error" class="image-slot">
							<img src="../../../public/img/error.png" style="width: 90px; height: 90px" />
						</div>
					</el-image>
							</el-form-item>
			</el-col>
					
	
	
	
	
	
	
			<el-col :xs="24" :sm="12" :lg="8" class="el_form_btn_warp">
				<el-form-item>
					<el-button type="primary" @click="submit()">提交</el-button>
					<el-button @click="cancel()">取消</el-button>
				</el-form-item>
			</el-col>

		</el-form>
	</el-main>
</template>

<script>
	import mixin from "@/mixins/page.js";

	export default {
		mixins: [mixin],
		data() {
			return {
				field: "seat_information_id",
				url_add: "~/api/seat_information/add?",
				url_set: "~/api/seat_information/set?",
				url_get_obj: "~/api/seat_information/get_obj?",
				url_upload: "~/api/seat_information/upload?",

				query: {
					"seat_information_id": 0,
				},

				form: {
								"seat_number":  '', // 座位号码
										"number_of_seats":  '', // 座位人数
										"seat_status":  '', // 座位状态
										"seat_image":  '', // 座位图片
											"seat_information_id": 0, // ID
						
				},
				disabledObj:{
								"seat_number_isDisabled": false,
										"number_of_seats_isDisabled": false,
										"seat_status_isDisabled": false,
										"seat_image_isDisabled": false,
										},

	
		
						// 座位状态选项列表
				list_seat_status: ['未开桌','已开桌'],
	
		
	
			}
		},
		methods: {


	
	
			
	
			
	
						/**
			 * 上传座位图片
			 * @param {Object} param 图片参数
			 */
			upload_seat_image(param){
						this.uploadFile(param.file, "seat_image");
					},
	
	
		
			/**
			 * 获取对象之前
			 * @param {Object} param
			 */
			get_obj_before(param) {
				var form = "";
												
				if(this.form && form){
					Object.keys(this.form).forEach(key => {
						Object.keys(form).forEach(dbKey => {
							// if(dbKey === "charging_standard"){
							// 	this.form['charging_rules'] = form[dbKey];
							// 	this.disabledObj['charging_rules_isDisabled'] = true;
							// };
							if(key === dbKey){
								this.disabledObj[key+'_isDisabled'] = true;
							}
						})
					})
				}
												$.db.del("form");
				return param;
			},

			/**
			 * 获取对象之后
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json, func){


												


			},

			/**
			 * 提交前验证事件
			 * @param {Object} 请求参数
			 * @return {String} 验证成功返回null, 失败返回错误提示
			 */
			submit_check(param) {
				let msg = null
																		return msg;
			},

			is_view(){
				var bl = this.user_group == "管理员";

				if(!bl){
					bl = this.$check_action('/seat_information/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/seat_information/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/seat_information/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/seat_information/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/seat_information/view','get');
					console.log(bl ? "你有视图查询权限视作有查询权限" : "你没有视图查询权限");
				}

				console.log(bl ? "具有当前页面的查看权，请注意这不代表你有字段的查看权" : "无权查看当前页，请注意即便有字段查询权限没有页面查询权限也不行");

				return bl;
			},
			/**
			 * 上传文件
			 * @param {Object} param
			 */
			uploadimg(param) {
				this.uploadFile(param.file, "avatar");
			},

		},
		created() {
										},
	}
</script>

<style>
	.avatar-uploader .el-upload {
		border: 1px dashed #d9d9d9;
		border-radius: 6px;
		cursor: pointer;
		position: relative;
		overflow: hidden;
	}

	.avatar-uploader .el-upload:hover {
		border-color: #409EFF;
	}

	.avatar-uploader-icon {
		font-size: 28px;
		color: #8c939d;
		width: 178px;
		height: 178px;
		line-height: 178px;
		text-align: center;
	}

	.avatar {
		width: 178px;
		height: 178px;
		display: block;
	}




</style>
