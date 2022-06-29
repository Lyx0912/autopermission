<template>
  <div>
    <div class="headInfo">
      <span></span>
    </div>
    <el-table
      @selection-change="handleSelectionChange"
      :data="permList"
      style="width: 100%;margin: 15px;"
      row-key="id"
      default-expand-all
      :tree-props="{children: 'children', hasChildren: 'hasChildren'}">
      <el-table-column
        type="selection"
        width="55"></el-table-column>
      <el-table-column
        prop="permName"
        label="菜单名称"
        width="180">
      </el-table-column>
      <el-table-column
        label="图标"
        width="100">
        <template slot-scope="scope">
          <svg-icon :icon-class="scope.row.icon" />
        </template>
      </el-table-column>
      <el-table-column
        prop="sort"
        label="排序"
        width="100">
      </el-table-column>
      <el-table-column
        label="类型"
        width="100">
        <template slot-scope="scope">
          <span>{{scope.row.type ==0?'目录':'菜单'}}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="path"
        label="uri">
      </el-table-column>
      <el-table-column
        prop="component"
        label="组件路径">
      </el-table-column>
      <el-table-column
        prop="component"
        label="权限标识">
      </el-table-column>
      <el-table-column
        width="280"
        label="操作">
        <template slot-scope="scope">
          <el-button type="primary" size="small" @click="handleUpdate(scope.row.id)">编辑</el-button>
          <el-button type="success" size="small" @click="handleAdd(scope.row.id)">新增</el-button>
          <el-button type="danger" size="small" @click="remove(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog title="提示" :visible.sync="dialogVisible" width="30%" :before-close="handleClose">
      <el-form ref="permission" :model="permission" :rules="rules" label-width="80px">
        <el-row>
          <el-col :span="24">
            <el-form-item label="上级菜单">
              <el-cascader
                v-model="permission.parentId"
                :options="parentMenus"
                :show-all-levels="false"
                :placeholder="placeholder"
                :props="{ checkStrictly: true,label:'permName',value:'id' }"
                @change="handleChange"></el-cascader>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="菜单类型" prop="type">
              <el-radio-group v-model="permission.type">
                <el-radio label="0">目录</el-radio>
                <el-radio label="1">菜单</el-radio>
                <el-radio label="2">按钮</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
          <el-col :span="24" >
            <el-form-item label="菜单名称" prop="permName">
              <el-input v-model="permission.permName"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item  label="菜单图标">
              <el-popover
                placement="bottom-start"
                width="460"
                trigger="click"
                @show="$refs['iconSelect'].reset()"
              >
                <IconSelect ref="iconSelect" @selected="selected" />
                <el-input slot="reference" v-model="permission.icon" placeholder="点击选择图标" readonly>
                  <svg-icon
                    v-if="permission.icon"
                    slot="prefix"
                    :icon-class="permission.icon"
                    class="el-input__icon"
                    style="height: 32px;width: 16px;"
                  />
                  <i v-else slot="prefix" class="el-icon-search el-input__icon" />
                </el-input>
              </el-popover>
            </el-form-item>
          </el-col>
          <el-col :span="24" >
            <el-form-item label="url">
              <el-input v-model="permission.path"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="24" v-if="permission.type == 1">
            <el-form-item label="组件路径">
              <el-input v-model="permission.component"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12" >
            <el-form-item label="权限标识">
              <el-input v-model="permission.perms"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12" >
            <el-form-item label="排序">
              <template>
                <el-input-number v-model="permission.sort" @change="handleChange" :min="1" :max="10" label="描述文字"></el-input-number>
              </template>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="dialogVisible = false">取 消</el-button>
      <el-button type="primary" @click="saveOrUpdate">确 定</el-button>
    </div>
    </el-dialog>
  </div>
</template>

<script>
import { list,add,remove,getInfo,update } from "@/api/perm"
import Treeselect from "@riophae/vue-treeselect";
import IconSelect from "@/components/IconSelect";
import {rm} from "@/api/user";

export default {
  name: "index",
  components: { Treeselect, IconSelect },
  data(){
    return {
      placeholder:'请选择',
      // 添加/更新菜单对话框
      dialogVisible:false,
      parentMenus:[
        {
          id : 0,
          permName: '一级目录',
          children: []
        }
      ],
      // id
      ids: '',
      // 权限列表
      permList:[],
      permission:{},
      rules:{
        permName : {required: true, message: '请输入菜单名称', trigger: 'blur'},
      },
    }
  },
  created() {
    this.getList()
  },
  methods:{
    // 选择图标
    selected(name) {
      this.$forceUpdate();
      this.permission.icon = name;
    },
    // 添加菜单
    handleAdd(id){
      this.permission = {}
      this.permission.parentId = id;
      this.dialogVisible = true;
    },
    // 编辑菜单
    handleUpdate(id){
      this.info(id)
      this.dialogVisible = true;
    },
    getList(){
      list().then(Response=>{
        this.permList = Response.data
        this.parentMenus[0].children = this.permList
        this.parentMenus = this.deleteChildren(this.parentMenus)
      })
    },
    // 删除空children
    deleteChildren(arr) {
      let childs = arr
      for (let i = childs.length; i--; i > 0) {
        if (childs[i].children) {
          if (childs[i].children.length) {
            this.deleteChildren(childs[i].children)
          } else {
            delete childs[i].children
          }
        }
      }
      return arr
    },
    saveOrUpdate(){
      if(this.permission.id == undefined){
        add(this.permission).then(Response=>{
          if(Response.code == 0){
            this.$message.success("添加成功")
          }
        })
      }else{
        this.updatePerm()
      }
      this.getList()
      this.dialogVisible = false
    },
    // 批量
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    // 删除菜单
    remove(uid){
      const ids = uid || this.ids;
      this.$confirm('是否确认删除用户编号为"' + ids + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function () {
        return remove(ids);
      }).then(() => {
        this.getList();
        this.$message.success("删除成功");
      }).catch(() => {
      });
      // rm(ids).then(Response=>{
      //   if(Response.code == 0){
      //     this.$message.success("删除成功")
      //   }
      // })
    },
    info(id){
      this.$forceUpdate();
      getInfo(id).then(Response=>{
        if(Response.code === 0){
          this.permission = Response.data
          if(this.permission.parentId == 0){
            this.placeholder = '一级目录'
          }
        }
      })
    },
    updatePerm(){
      update(this.permission).then(Response=>{
        if(Response.code ==0){
          this.$message.success("更新成功")
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
</style>
