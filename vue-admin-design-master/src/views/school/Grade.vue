<template>
  <div class="table-classic-wrapper">
    <el-card shadow="always">
      <!-- 操作栏 -->
      <div class="control-btns">
        <el-button type="primary" @click="formVisible=true,form = {}">新建数据</el-button>
        <el-button type="primary" @click="handleImport">导入数据</el-button>
        <el-button type="primary" @click="exportVisible = true">导出数据</el-button>
        <el-button type="danger" @click="batchDelete">批量删除</el-button>
      </div>
      <!-- 查询栏 -->
      <el-form
        ref="searchForm"
        :inline="true"
        :model="listQuery"
        label-width="90px"
        class="search-form"
      >

        <el-form-item label="请选择学校" prop="school">

          <el-select v-model="listQuery.school">
            <el-option :value="'学校1'" label="学校1"/>
            <el-option :value="'学校2'" label="学校2"/>
            <el-option :value="'学校3'" label="学校3"/>
            <el-option :value="'学校4'" label="学校4"/>
            <el-option :value="'学校5'" label="学校5"/>
            <el-option :value="'学校6'" label="学校6"/>
            <el-option :value="'学校7'" label="学校7"/>
            <el-option :value="'学校8'" label="学校8"/>


          </el-select>
        </el-form-item>
        <el-form-item label="年级" prop="name">

          <el-select v-model="listQuery.name">
            <el-option :value="'一年级'" label="一年级"/>
            <el-option :value="'二年级'" label="二年级"/>
            <el-option :value="'三年级'" label="三年级"/>
            <el-option :value="'四年级'" label="四年级"/>
            <el-option :value="'五年级'" label="五年级"/>
            <el-option :value="'六年级'" label="六年级"/>
            <el-option :value="'初一'" label="初一"/>
            <el-option :value="'初二'" label="初二"/>
            <el-option :value="'初三'" label="初三"/>
            <el-option :value="'高一'" label="高一"/>
            <el-option :value="'高二'" label="高二"/>
            <el-option :value="'高三'" label="高三"/>

          </el-select>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="search">查询</el-button>
          <el-button type="info" @click="reset">重置</el-button>
        </el-form-item>
      </el-form>
      <!-- 表格栏 -->
      <el-table
        ref="multipleTable"
        v-loading="listLoading"
        :data="tableData"
        tooltip-effect="dark"
        style="width: 100%"
        size="medium"
        border="true"
      >
        <el-table-column type="selection" width="42"/>
        <el-table-column prop="id" label="ID" align="center" width="60" sortable/>
        <el-table-column prop="name" label="年级名称" align="center">
        </el-table-column>
        <el-table-column prop="master" label="年级主任姓名"/>
        <el-table-column prop="number" label="年级总人数"/>
        <el-table-column prop="classNumber" label="班级总人数"/>

        <el-table-column prop="detail" label="年级描述" how-overflow-tooltip>
        </el-table-column>
        <el-table-column label="操作" align="center" width="250">
          <template slot-scope="scope">
            <el-button type="success" @click="getii1(scope.row),detail" style="magin-right: 10px;">查看详情</el-button>
            <el-button @click="getii(scope.row)">编辑</el-button>
            <el-button type="danger" @click="del(scope.row.id)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <!-- 分页栏 -->
      <Pagination :total="total" :page.sync="listQuery.currentPage" :limit.sync="listQuery.pageSize"
                  @pagination="fetchData"/>

      <!-- 添加对话框 -->
      <el-dialog
        title="添加"
        :visible.sync="formVisible"
        width="40%"
        class="dialog-form"
        :before-close="handleClose"
      >
        <el-form
          ref="dialogForm"
          :model="dialogForm"
          :rules="formRules"
          label-width="100px"
        >
          <el-form-item label="年级" prop="name">

            <el-select v-model="dialogForm.name">
              <el-option :value="'一年级'" label="一年级"/>
              <el-option :value="'二年级'" label="二年级"/>
              <el-option :value="'三年级'" label="三年级"/>
              <el-option :value="'四年级'" label="四年级"/>
              <el-option :value="'五年级'" label="五年级"/>
              <el-option :value="'六年级'" label="六年级"/>
              <el-option :value="'初一'" label="初一"/>
              <el-option :value="'初二'" label="初二"/>
              <el-option :value="'初三'" label="初三"/>
              <el-option :value="'高一'" label="高一"/>
              <el-option :value="'高二'" label="高二"/>
              <el-option :value="'高三'" label="高三"/>

            </el-select>
          </el-form-item>
          <el-form-item label="年级主任">
            <el-input v-model="dialogForm.master"></el-input>
          </el-form-item>
          <el-form-item label="年级总人数">
            <el-input v-model="dialogForm.number"></el-input>
          </el-form-item>
          <el-form-item label="年级描述">
            <el-input v-model="dialogForm.detail"></el-input>
          </el-form-item>


          <div class="footer-item">
            <el-button @click="formVisible=false">取 消</el-button>

            <el-button type="primary" :disabled="isSubmit" @click="add">确 定</el-button>
          </div>
        </el-form>
      </el-dialog>

      <!--编辑对话框 -->

      <el-dialog
        title="编辑"
        :visible.sync="formVisible1"
        width="40%"
        class="dialog-form"
        :before-close="handleClose1"
      >
        <el-form
          ref="dialogForm1"
          :model="dialogForm1"
          :rules="formRules"
          label-width="100px"
        >
          <el-form-item label="年级" prop="name">

            <el-select v-model="dialogForm1.name">
              <el-option :value="'一年级'" label="一年级"/>
              <el-option :value="'二年级'" label="二年级"/>
              <el-option :value="'三年级'" label="三年级"/>
              <el-option :value="'四年级'" label="四年级"/>
              <el-option :value="'五年级'" label="五年级"/>
              <el-option :value="'六年级'" label="六年级"/>
              <el-option :value="'初一'" label="初一"/>
              <el-option :value="'初二'" label="初二"/>
              <el-option :value="'初三'" label="初三"/>
              <el-option :value="'高一'" label="高一"/>
              <el-option :value="'高二'" label="高二"/>
              <el-option :value="'高三'" label="高三"/>

            </el-select>
          </el-form-item>
          <el-form-item label="年级主任">
            <el-input v-model="dialogForm1.master"></el-input>
          </el-form-item>
          <el-form-item label="年级总人数">
            <el-input v-model="dialogForm1.number"></el-input>
          </el-form-item>
          <el-form-item label="班级总人数">
            <el-input v-model="dialogForm1.classNumber"></el-input>
          </el-form-item>
          <el-form-item label="年级描述">
            <el-input v-model="dialogForm1.detail"></el-input>
          </el-form-item>
          <div class="footer-item">
            <el-button @click="formVisible1 = false">取 消</el-button>
            <el-button type="primary" :disabled="isSubmit" @click="update">确 定</el-button>
          </div>
        </el-form>
      </el-dialog>


      <!-- 查看详情数据回显 -->
      <el-dialog
        title="详细信息"
        :visible.sync="formVisible2"
        width="40%"
        class="dialog-form"
        :before-close="handleClose2"
      >
        <el-form
          ref="dialogForm2"
          :model="dialogForm2"
          :rules="formRules"
          label-width="100px"
        >
          <el-form-item label="学校名称">
            <el-input v-model="dialogForm2.name1"></el-input>
          </el-form-item>
          <el-form-item label="学校校长">
            <el-input v-model="dialogForm2.master1"></el-input>
          </el-form-item>
          <el-form-item label="学校地址">
            <el-input v-model="dialogForm2.address"></el-input>
          </el-form-item>
          <el-form-item label="学校总人数">
            <el-input v-model="dialogForm2.numberStudent"></el-input>
          </el-form-item>
          <el-form-item label="学校总年级数">
            <el-input v-model="dialogForm2.numberGrade"></el-input>
          </el-form-item>
          <el-form-item label="学校总班级数">
            <el-input v-model="dialogForm2.numberClass"></el-input>
          </el-form-item>
          <el-form-item label="学校描述">
            <el-input v-model="dialogForm2.detail1"></el-input>
          </el-form-item>
          <el-form-item label="年级ID">
            <el-input v-model="dialogForm2.id"></el-input>
          </el-form-item>
          <el-form-item label="年级名称">
            <el-input v-model="dialogForm2.name"></el-input>
          </el-form-item>
          <el-form-item label="年级主任姓名">
            <el-input v-model="dialogForm2.master"></el-input>
          </el-form-item>
          <el-form-item label="年级总人数">
            <el-input v-model="dialogForm2.number"></el-input>
          </el-form-item>
          <el-form-item label="年级描述">
            <el-input v-model="dialogForm2.detail"></el-input>
          </el-form-item>

          <!-- <div class="footer-item">
            <el-button @click="formVisible2 = false">取 消</el-button>
            <el-button type="primary" :disabled="true">确 定</el-button>
          </div> -->
        </el-form>
      </el-dialog>


      <!-- 导入数据 弹出栏 -->
      <el-dialog
        title="导入数据"
        :visible.sync="importVisible"
        width="20%"
      >
        <div class="upload-box">
          <span>选择文件：</span>
          <Upload :files-format="filesFormat">
            <i class="vue-dsn-icon-upload"/>上传文件
          </Upload>
        </div>
        <div class="hints">TIP：请选择要导出数据的格式。</div>
        <span slot="footer">
            <el-button @click="cancleImport">取 消</el-button>
            <el-button type="primary" @click="confirmImport">确 定</el-button>
          </span>
      </el-dialog>
      <!-- 导出数据 弹出栏 -->
      <el-dialog
        title="导出数据"
        :visible.sync="exportVisible"
        width="30%"
      >
        <div class="export-data">
          <el-button type="primary" @click="exportTable('xlsx')">EXCEL格式</el-button>
          <el-button type="primary" @click="exportTable('csv')">CSV格式</el-button>
          <el-button type="primary" @click="exportTable('txt')">TXT格式</el-button>
        </div>
        <div class="hints">TIP：请选择要导出数据的格式。</div>
      </el-dialog>
    </el-card>
  </div>
</template>

<script>
// import { getTableList } from '@/api'
import excel from '@/utils/excel'
import Pagination from '@/components/Pagination'
import Upload from '@/components/Upload'
import Hints from '@/components/Hints'

export default {
  name: 'Grade',
  components: {Pagination, Upload, Hints},
  data() {
    return {
      formVisible1: false,
      formVisible2: false,
      // 数据列表加载动画
      listLoading: true,
      // 查询列表参数对象
      listQuery: {
        name: '',
        currentPage: 1,
        pageSize: 10
      },
      // 新增/编辑提交表单对象
      dialogForm: {
        name: undefined,
        phone: undefined,
        married: undefined,
        hobby: []
      },
      dialogForm1: {
        name: undefined,
        phone: undefined,
        married: undefined,
        hobby: []
      },
      dialogForm2: {},
      // 数据总条数
      total: 0,
      // 表格数据数组
      tableData: [],
      // 多选数据暂存数组
      multipleSelection: [],
      // 新增/编辑 弹出框显示/隐藏
      formVisible: false,
      // 表单校验 trigger: ['blur', 'change'] 为多个事件触发
      formRules: {
        name: [
          {required: true, message: '请输入姓名', trigger: 'blur'}
        ],
        phone: [
          {required: true, message: '请输入手机号', trigger: 'blur'},
          {pattern: /^1[3456789]\d{9}$/, message: '请输入正确的手机号', trigger: 'blur'}
        ]
      },
      // 防止多次连续提交表单
      isSubmit: false,
      // 导入数据 弹出框显示/隐藏
      importVisible: false,
      // 导出文件格式
      filesFormat: '.txt, .csv, .xls, .xlsx',
      // 导出数据 弹出框显示/隐藏
      exportVisible: false
    }
  },
  created() {

  },

  mounted() {

    //渲染表格数据
    const that = this
    this.$axios.get("http://localhost:8086/grade/select", {})
      .then(function (response) {
        console.log(response);
        console.log(response.data)
        that.tableData = response.data
        that.listLoading = false
      })
      .catch(function (error) {
        console.log(error);
      });
  },

  methods: {
    reset() {
      const that = this
      that.listQuery = {}
      that.load()

    },

    load() {
      const that = this
      this.$axios.get("http://localhost:8086/grade/select", {})
        .then(function (response) {
          console.log(response);
          console.log(response.data)
          that.tableData = response.data
          t
          this.form = null;
        })
        .catch(function (error) {
          console.log(error);
        });
    },

    getii(row) {
      const that = this

      console.log(row)
      this.dialogForm1 = JSON.parse(JSON.stringify(row))
      that.formVisible1 = true;

    },


    getii1(row) {
      const that = this
      this.detail(row)
      console.log(row)
      this.dialogForm2 = JSON.parse(JSON.stringify(row))
      that.formVisible2 = true;

    },

    // 多选操作
    // handleSelectionChange(val) {
    //   this.multipleSelection = val
    // },
    // 新建数据
    add() {
      const that = this
      console.log(this.dialogForm)
      this.$axios.post("http://localhost:8086/grade/add", this.dialogForm
      )
        .then(function (response) {
          console.log(response);

          alert("添加成功");
          that.formVisible = false;
          that.load();
        })
        .catch(function (error) {
          console.log(error);
        });


      // this.formVisible = true
      // this.dialogForm.name = undefined
      // this.dialogForm.phone = undefined
      // this.dialogForm.married = undefined
      // this.dialogForm.hobby = []
    },
    update() {
      const that = this
      console.log(this.dialogForm1)
      this.$axios.post("http://localhost:8086/grade/update", this.dialogForm1
      )
        .then(function (response) {
          console.log(response);
          that.formVisible1 = false;
          alert("修改成功");
          that.load();
        })
        .catch(function (error) {
          console.log(error);
        });
    },

    //删除
    del(id) {
      const that = this
      //  const id = scope.row.id
      this.$axios.post("http://localhost:8086/grade/delete" + id, {}
      )
        .then(function (response) {
          console.log(response);
          alert("删除成功")
          that.load();
        })
        .catch(function (error) {
          console.log(error);
        });
    },


    search() {
      const that = this
      let form = new FormData()
      form.append('name', this.listQuery.name)

      this.$axios.post("http://localhost:8086/grade/search", form)
        .then(function (response) {
          that.tableData = response.data.data
          console.log(response.data.data)
        })
        .catch(function (error) {
          console.log(error);
        });

    },


    //查看详情多表数据回显
    detail(row) {
      const that = this
      let form = new FormData()
      console.log(row)
      form.append('id', row.id)

      this.$axios.post("http://localhost:8086/grade/detail", form)
        .then(function (response) {
          that.dialogForm2 = response.data[0]
          console.log(response.data)

        })
        .catch(function (error) {
          console.log(error);
        });

    },


    // 编辑数据
    // handleEdit(index, row) {
    //   console.log(index, row)
    //   this.formVisible = true
    //   this.dialogForm.name = row.name
    //   this.dialogForm.phone = row.phone
    //   this.dialogForm.married = row.married
    //   this.dialogForm.hobby = row.hobby.split('、')
    // },
    // 删除数据
    handleDelete(index, row) {
      console.log(index, row)
      this.$confirm('此操作将删除选中数据, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        // 此处可添加--删除接口
        // 删除成功调用fetchData方法更新列表
        this.$message({
          type: 'success',
          message: '删除成功!'
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
    },
    // 批量删除
    batchDelete() {
      if (this.multipleSelection.length < 1) {
        this.$message({
          message: '请先选择要删除的数据！',
          type: 'warning'
        })
      } else {
        this.handleDelete()
      }
    },
    // 新增/编辑弹出框 关闭时操作
    handleClose() {
      this.formVisible = false
      this.$refs.dialogForm.resetFields()
    },
    handleClose1() {
      this.formVisible1 = false
      this.$refs.dialogForm1.resetFields()
    },
    handleClose2() {
      this.formVisible2 = false
      this.$refs.dialogForm2.resetFields()
    },
    // 获取数据列表
    fetchData() {
      this.listLoading = true
      // 获取数据列表接口
      getTableList(this.listQuery).then(res => {
        const data = res.data
        if (data.code === 0) {
          this.total = data.data.total
          this.tableData = data.data.list
          this.listLoading = false
        }
      }).catch(() => {
        this.listLoading = false
      })
    },
    // 查询数据
    onSubmit() {
      this.listQuery.currentPage = 1
      this.fetchData()
    },
    // 导入数据
    handleImport() {
      this.importVisible = true
    },
    // 新增/编辑表单确认提交
    submitForm(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          // 此处添加 新增/编辑数据的接口 新增成功后调用fetchData方法更新列表
          // 先 this.isSubmit = true 接口返回成功后 再 this.isSubmit = false
          this.formVisible = false
        } else {
          this.isSubmit = false
          return false
        }
      })
    },
    // 新增/编辑表单取消提交
    cancleForm() {
      this.$refs.dialogForm.resetFields()
      this.formVisible = false
    },
    // 导入数据弹出栏 确认操作
    confirmImport() {
      // 此处添加 后台接收的接口，将导入的数据传给后台处理
      this.importVisible = false
    },
    // 导入数据弹出栏 取消操作
    cancleImport() {
      // 将导入的数据清空
      this.importVisible = false
    },
    // 导出数据--excle格式
    exportTable(type) {
      if (this.tableData.length) {
        const params = {
          header: ['编号', '姓名', '性别', '手机', '学历', '婚姻状况', '禁止编辑', '爱好'],
          key: ['id', 'name', 'sex', 'phone', 'education', 'married', 'forbid', 'hobby'],
          data: this.tableData,
          autoWidth: true,
          fileName: '综合表格',
          bookType: type
        }
        excel.exportDataToExcel(params)
        this.exportVisible = false
      }
    },
    // 列表中婚姻状况栏，状态值改变时，调用
    selectChange(row) {
      // 此处添加后台接口，成功后调用fetchData方法更新列表
    },
    // 列表中禁止编辑栏，状态值改变时，调用
    stateChange(row) {
      // 此处添加后台接口，成功后调用fetchData方法更新列表
    }
  }
}
</script>

<style lang="less">
.table-classic-wrapper {
  .el-card {
    min-height: 656px;
  }

  .control-btns {
    margin-bottom: 20px;
  }

  .search-form {
    padding-top: 18px;
    margin-bottom: 15px;
    background-color: #f7f8fb;
  }

  .el-table thead {
    font-weight: 600;

    th {
      background-color: #f2f3f7;
    }
  }

  .dialog-form {
    .el-input {
      width: 380px;
    }

    .footer-item {
      margin-top: 50px;
      text-align: right;
    }
  }

  .upload-box,
  .export-data {
    width: 300px;
    margin: 0 auto 30px;
  }

  .upload-box {
    width: 156px;

    .files-upload {
      color: #20a0ff;
    }
  }

  .hints {
    font-size: 12px;
    color: #aaa;
    text-align: center;
  }
}
</style>



