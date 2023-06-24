<script setup lang="ts">
import { onMounted, ref }                                                           from "vue";
import { addSingleRecord, deleteSingleRecord, getSingleRecord, updateSingleRecord } from "../services/record";
import { Record, RecordRequest }                                                    from "../types/record";
import { ElNotification }                                                           from "element-plus";
import { router }                                                                   from "../router/index";
import { postUserRecordHistory }                                                    from "../services/history";
import { RecordAction }                                                             from "../consts/action";

const props = defineProps<{
  recordId: number
}>()

const record = ref<Record>({
  title: '',
  description: '',
  content: '',
  type: '',
} as Record)

// 上传记录
const uploadRecord = () => {
  // 如果 recordId 不为 0，说明是编辑记录
  if (props.recordId != 0) {
    updateSingleRecord(record.value.recordId, {
      title: record.value.title,
      description: record.value.description,
      content: record.value.content,
      type: record.value.type
    })

    // 添加更新历史
    postUserRecordHistory(props.recordId, RecordAction.UPDATE)

    ElNotification({
      title: '编辑成功',
      message: '记录编辑成功',
      type: 'success',
    })

    router.back()
    return
  }

  // 否则是添加记录
  addSingleRecord(record.value as RecordRequest).then(() => {
    ElNotification({
      title: '上传成功',
      message: '记录上传成功',
      type: 'success',
    })

    // 添加创建历史
    postUserRecordHistory(props.recordId, RecordAction.CREATE)

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

// 删除记录
const deleteRecord = () => {
  deleteSingleRecord(props.recordId).then(() => {
    ElNotification({
      title: '删除成功',
      message: '记录删除成功',
      type: 'success',
    })

    // 添加删除历史
    postUserRecordHistory(props.recordId, RecordAction.DELETE)

    // 返回上一个页面
    router.back()
  }).catch(() => {
    ElNotification({
      title: '删除失败',
      message: '记录删除失败',
      type: 'error',
    })
  })
}

onMounted(() => {
  if (props.recordId != 0) {
    getSingleRecord(props.recordId).then(res => {
      record.value = res
    })
  }
})
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

      <el-popconfirm title="确定删除这条记录？" @confirm="deleteRecord" v-if="props.recordId != 0">
        <template #reference>
          <el-button type="danger" style="float: right; margin: 0 24px">删除记录</el-button>
        </template>
      </el-popconfirm>
      <el-button type="primary" @click="uploadRecord" style="float: right">{{ props.recordId == 0 ? '上传记录' : '保存记录' }}</el-button>
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
