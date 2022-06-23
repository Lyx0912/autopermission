<template>
  <div>
    <div class="headInfo">
      <span>存放头部信息</span>
    </div>
    <el-table
      @selection-change="handleSelectionChange"
      :data="userList"
      border
      style="width: 100%; margin: 15px">
      <el-table-column
        type="selection"
        width="55"></el-table-column>
      <el-table-column
        prop="name"
        label="姓名"
        width="120">
      </el-table-column>
      <el-table-column
        prop="username"
        label="用户名"
        width="120">
      </el-table-column>
      <el-table-column
        label="性别"
        width="120">
        <template slot-scope="scope">
          <span>{{ scope.row.sex == 0 ? '女' : '男' }}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="phone"
        label="手机号"
        width="120">
      </el-table-column>
      <el-table-column
        label="状态"
        width="120">
        <template slot-scope="scope">
          <span>{{ scope.row.enable == 0 ? '注销' : '正常' }}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="address"
        label="地址"
        width="350">
      </el-table-column>
      <el-table-column
        :show-overflow-tooltip="true"
        label="角色信息">
        <template slot-scope="scope">
          <el-tag
            style="margin-right: 5px"
            v-for="role in scope.row.roles"
            @close="handleClose(scope.row.id,role)"
            :key="role"
            closable>
            {{ role }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column
        fixed="right"
        label="操作"
        width="250">
        <template slot-scope="scope">
          <el-button class="button-new-tag" size="small" @click="showInput(scope.row.id)">+ 角色</el-button>
          <el-button @click="handleClick(scope.row)" type="primary" size="small">编辑</el-button>
          <el-button type="danger" size="small">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog
      title="请选择角色"
      :visible.sync="dialogVisible"
      width="20%"
      label-width="100px"
      :before-close="handleClose">
      <el-form >
        <el-form-item label="角色" prop="type">
          <el-select v-model="roleV" filterable placeholder="请选择">
            <el-option
              v-for="item in roleList"
              :key="item.id"
              :label="item.roleName"
              :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="addRole">确 定</el-button>
  </span>
    </el-dialog>

    <el-pagination
      background
      v-show="total>0"
      :total="total"
      layout="prev, pager, next"
      :current-page="queryParams.pageNum"
      :page-size="queryParams.pageSize"
      @current-change="getList"
    >
    </el-pagination>


  </div>
</template>

<script>

import {mapGetters} from "vuex";
import {list, removeRole} from "@/api/user";
import { allRole,add } from "@/api/role"

export default {
  name: 'userManager',
  computed: {
    ...mapGetters([
      'name'
    ])
  },
  data() {
    return {
      uid: '',
      dialogVisible: false,
      ids: '',
      total: 0,
      userList: [],
      queryParams: {
        pageNum: 1,
        pageSize: 2
      },
      roleList:[],
      roleV: ''
    }
  },
  created() {
    this.getList()
    this.allRole()
  },
  methods: {
    // 所有角色的列表
    allRole(){
      allRole().then(Response=>{
        this.roleList = Response.data
      })
    },
    // 添加角色
    showInput(uid) {
      this.uid = uid
      this.dialogVisible = true
    },
    // 获取用户列表
    getList(page = 1) {
      this.queryParams.pageNum = page
      list(this.queryParams).then(res => {
        this.userList = res.data.records
        this.total = res.data.total
      })
    },
    // 删除角色信息
    handleClose(id, role) {
      removeRole(id, role).then(Response => {
        if (Response.code == 0) {
          this.$message.success("删除成功")
          this.getList(this.queryParams.pageNum)
        }
      })
    },
    // 批量
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    // 添加角色
    addRole(){
      console.log(this.uid)
      add(this.uid,this.roleV).then(Response=>{
        if(Response.code == 0){
          console.log(Response)
          this.$message.success("添加成功")
          this.dialogVisible = false
          this.getList(this.queryParams.pageNum)
        }
      })
    }
  }
}
</script>

<style scoped>
.headInfo {
  width: 100%;
  height: 150px;
}
.el-dialog .el-dialog__body{
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>
