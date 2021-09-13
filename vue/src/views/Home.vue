<template>
  <div style="padding: 10px">
    <div style="margin: 10px 0">
      <el-button @click="addUser" type="primary">新增</el-button>
      <el-button type="primary">导入</el-button>
      <el-button type="primary">导出</el-button>
    </div>
    <div style="padding: 10px 0">
      <el-input v-model="search" placeholder="请输入搜索关键字" style="width: 20%"></el-input>
      <el-button @click="load" type="primary" style="margin-left: 5px">查询</el-button>
    </div>
    <el-table :data="tableData" border style="width: 100%">
      <el-table-column prop="id" label="id" sortable=""></el-table-column>
      <el-table-column prop="username" label="姓名"></el-table-column>
      <el-table-column prop="nickName" label="昵称"></el-table-column>
      <el-table-column prop="age" label="年龄"></el-table-column>
      <el-table-column prop="sex" label="性别"></el-table-column>
      <el-table-column prop="address" label="地址"></el-table-column>
      <el-table-column prop="fixed" label="操作">
        <template #default="scope">
          <el-button @click="handleEdit(scope.row)" type="primary" size="small">编辑</el-button>
          <el-popconfirm title="这是一段内容确定删除吗？">
            <template #reference>
              <el-button type="danger" size="small">删除</el-button>
            </template>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <div style="padding: 10px 0">
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="currentPage"
        :page-sizes="[10, 20, 30, 40]"
        :page-size="pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total">
      </el-pagination>
    </div>
    <el-dialog title="提示" v-model="dialogVisible" width="30%">
      <el-form :model="form" label-width="120px">
        <el-form-item label="姓名" style="width: 80%">
          <el-input v-model="form.username"></el-input>
        </el-form-item>
        <el-form-item label="昵称" style="width: 80%">
          <el-input v-model="form.nickName"></el-input>
        </el-form-item>
        <el-form-item label="年龄" style="width: 80%">
          <el-input v-model="form.age"></el-input>
        </el-form-item>
        <el-form-item label="性别" style="width: 80%">
          <el-radio v-model="form.sex" label="1">男</el-radio>
          <el-radio v-model="form.sex" label="2">女</el-radio>
          <el-radio v-model="form.sex" label="3">未知</el-radio>
        </el-form-item>
        <el-form-item label="地址" style="width: 80%">
          <el-input type="textarea" v-model="form.address"></el-input>
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="dialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="save">确 定</el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<script>
import request from '../utils/request'

export default {
  name: 'Home',
  components: {},
  data() {
    return {
      form: {},
      dialogVisible: false,
      search: '',
      currentPage: 1,
      pageSize: 10,
      total: 0,
      tableData: []
    }
  },
  created() {
    this.load()
  },
  methods: {
    handleEdit(row) {
      this.form = JSON.parse(JSON.stringify(row))
      this.dialogVisible = true
    },
    // 改变当前页大小时触发
    handleSizeChange(pageSize) {
      this.pageSize = pageSize
      this.load()
    },
    // 改变当前页码时触发
    handleCurrentChange(pageNum) {
      this.currentPage = pageNum
      this.load()
    },
    select() {
    },
    addUser() {
      this.dialogVisible = true
    },
    save() {
      request.post('/api/usr/add', this.form)
        .then(res => {
          console.log(res)
          this.load()
          this.dialogVisible = false
        })

      this.dialogVisible = false
    },
    load() {
      request.get('/api/usr/query', {
        params: {
          pageNum: this.currentPage,
          pageSize: this.pageSize,
          search: this.search
        }
      }).then(res => {
        this.tableData = res.data.records
        this.total = res.data.total
        console.log(res)
      })
    }
  }
}
</script>

<style scoped>

</style>
