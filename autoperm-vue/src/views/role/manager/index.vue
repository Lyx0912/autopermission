<template>
  <div>
    <el-table
      :data="roleList"
      style="width: 100%; margin: 15px">
      <el-table-column
        prop="roleName"
        label="角色名称"
        width="180">
      </el-table-column>
      <el-table-column
        prop="status"
        label="状态"
        width="120">
        <template slot-scope="scope">
          <span>{{scope.row.status==0?'禁用':'正常'}}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="createTime"
        label="创建时间"
        width="180">
      </el-table-column>
      <el-table-column
        :show-overflow-tooltip="true"
        label="菜单列表">
        <template slot-scope="scope" >
          <el-tag
            style="margin-right: 5px"
            v-for="menu in scope.row.menuList"
            @close="handleClose(scope.row.id,role)"
            :key="menu"
            closable>
            {{ menu }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column
        label="操作"
        width="250">
        <template slot-scope="scope">
          <el-button class="button-new-tag" size="small" @click="addPerm(scope.row.id)">+ 权限</el-button>
          <el-button @click="handleClick(scope.row)" type="primary" size="small">编辑</el-button>
          <el-button type="danger" size="small" @click="del(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-dialog
      title="请选择菜单"
      :visible.sync="dialogVisible"
      width="20%"
      label-width="100px"
      :before-close="handleClose">
      <el-row>
        <el-col :span="24">
          <el-form-item label="上级菜单">
            <el-cascader
              v-model="addPid"
              :options="parentMenus"
              :show-all-levels="false"
              :placeholder="placeholder"
              :props="{ checkStrictly: true,label:'permName',value:'id' }"
              @change="handleChange"></el-cascader>
          </el-form-item>
        </el-col>
      </el-row>
      <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="addPerm">确 定</el-button>
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
import {list,remove} from '@/api/role'
import { list as menuList } from '@/api/perm'


export default {
  name: "index",
  data(){
    return{
      addPid:'',
      dialogVisible:false,
      ids:'',
      roleList:[],
      queryParams : {
        pageNum : 1,
        pageSize : 15
      },
      total: 0,
    }
  },
  methods : {
    getList(page = 1){
      this.queryParams.pageNum = page
      list(this.queryParams).then(Response=>{
        this.roleList = Response.data.records
        this.total = Response.data.total
      })
    },
    del(rid){
      const ids = rid || this.ids;
      this.$confirm('是否确认删除用户编号为"' + ids + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function () {
        return remove(ids);
      }).then(() => {
        this.getList(this.queryParams.pageNum);
        this.$message.success("删除成功");
      }).catch(() => {
      });
    },
    // 添加权限
    showInput(rid) {
      this.rid = rid
      this.dialogVisible = true
    },
    getMenuList(){
      menuList().then(Response=>{
        this.parentMenus = Response.data
        this.parentMenus = this.deleteChildren(this.parentMenus)
      })
    }
  },

  created() {
    this.getList()
  }
}
</script>

<style scoped>
.headInfo {
  width: 100%;
  height: 150px;
}
</style>
