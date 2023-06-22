<script setup lang="ts">
import { ref }            from "vue";
import { addRecord }      from "../services/record";
import { RecordRequest }  from "../types/record";
import { ElNotification } from "element-plus";
import { router }         from "../router/index";

const record = ref<RecordRequest>({
  title: '',
  description: '',
  content: '',
  type: '',
})

// 上传记录
const uploadRecord = () => {
  addRecord(record.value).then(() => {
    ElNotification({
      title: '上传成功',
      message: '记录上传成功',
      type: 'success',
    })

    // 返回上一个页面
    router.back()
  }).catch(() => {
    ElNotification({
      title: '上传失败',
      message: '记录上传失败',
      type: 'error',
    })
  })
}
</script>

<template>
  <div id="record">
    <div class="card" style="width: 100%; margin-top: 32px">
      <div class="card-title">记录编辑</div>

      <el-row style="margin-top: 24px">
        <el-col :span="6" :offset="2">
          <el-form-item label="记录标题">
            <el-input v-model="record.title" placeholder="请输入记录标题" style="width: 225px"/>
          </el-form-item>
          <el-form-item label="记录类型">
            <el-select v-model="record.type" placeholder="请选择记录类型">
              <el-option label="会议记录" value="0100"/>
              <el-option label="工作记录" value="0200"/>
              <el-option label="案例纪录" value="0300"/>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="14">
          <el-form-item label="记录描述">
            <el-input v-model="record.description" type="textarea" placeholder="请输入记录描述" rows="3"/>
          </el-form-item>
        </el-col>
      </el-row>

      <el-button type="primary" @click="uploadRecord" style="margin-left: 110px">上传记录</el-button>
    </div>

    <div class="card" style="width: 100%; padding: 0; margin-top: 32px">
      <v-md-editor v-model="record.content" height="70vh"></v-md-editor>
    </div>
  </div>
</template>

<script lang="ts">
export default {
  name: "work-record-page"
}
</script>

<style scoped>
#record {
  padding: 0 240px 64px;
}
</style>
