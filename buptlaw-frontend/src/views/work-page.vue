<script setup lang="ts">
import { ref }                                from "vue";
import { Record }                             from "../types/record";
import { deleteSingleRecord, getRecordList }  from "../services/record";
import { getFormatTime }                      from "../utils/utils";
import { router }                             from "../router/index";
import { RECORD_STATUS_MAP, RECORD_TYPE_MAP } from "../consts/record";
import { ElNotification }                     from "element-plus";
import { postUserRecordHistory }   from "../services/history";
import { LawAction, RecordAction } from "../consts/action";

const recordList = ref<(Record & {
  formatType: string,
  formatStatus: string,
  formatUpdateTime: string,
})[]>([])

// 获取格式化后的记录列表
const getFormatRecordList = () => {
  getRecordList().then(res => {
    recordList.value = res.content.map(i => ({
      ...i,
      formatType: RECORD_TYPE_MAP[i.type],
      formatStatus: RECORD_STATUS_MAP[i.status],
      formatUpdateTime: i.updateDateTime ? getFormatTime(i.updateDateTime) : '未知',
    }))
  })
}

// 跳转到记录编辑页面
const toRecordEditPage = (recordId: number) => {
  // 添加浏览历史
  postUserRecordHistory(recordId, LawAction.BROWSE)

  router.push({name: 'record', params: {recordId: recordId}})
}

// 删除记录
const deleteRecord = (recordId: number) => {
  deleteSingleRecord(recordId).then(() => {
    ElNotification({
      title: '删除成功',
      message: '记录删除成功',
      type: 'success',
    })

    // 添加删除历史
    postUserRecordHistory(recordId, RecordAction.DELETE)

    getFormatRecordList()
  }).catch(() => {
    ElNotification({
      title: '删除失败',
      message: '记录删除失败',
      type: 'error',
    })
  })
}

getFormatRecordList()
</script>

<template>
  <div id="work">
    <div class="card" style="width: 100%; margin-top: 32px">
      <div class="card-title">记录列表</div>
      <el-button type="primary" style="float: right; margin-top: -32px" @click="toRecordEditPage(0)">添加记录</el-button>
      <el-table :data="recordList" stripe style="width: 100%; margin-top: 24px">
        <el-table-column prop="title" label="标题" width="180"/>
        <el-table-column prop="description" label="简述" width="540"/>
        <el-table-column prop="formatType" label="类型" width="120"/>
        <el-table-column prop="formatStatus" label="状态" width="120"/>
        <el-table-column prop="formatUpdateTime" label="更新时间" width="180"/>
        <el-table-column fixed="right" label="操作" min-width="180">
          <template #default="scope">
            <el-button plain @click="toRecordEditPage(scope.row.recordId)">查看</el-button>

            <el-popconfirm title="确定删除这条记录？" @confirm="deleteRecord(scope.row.recordId)">
              <template #reference>
                <el-button type="danger" plain >删除</el-button>
              </template>
            </el-popconfirm>

          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script lang="ts">
export default {
  name: "work-page"
}
</script>

<style scoped>
#work {
  padding: 0 240px 64px;
}
</style>
