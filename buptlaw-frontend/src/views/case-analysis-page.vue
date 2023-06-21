<script setup lang="ts">
import { getCaseList, getCaseReasonNums, getCaseStatusNums, getCaseTypeNums } from "../services/case";
import { onMounted, ref }                                                     from "vue";
import { Case }                                            from "../types/case";
import { getFormatTime }                                   from "../utils/utils";
import * as echarts                                        from 'echarts';

const caseList = ref<(Case & {
  formatOpenTime: string
})[]>([])

getCaseList(1, 10).then(res => {
  caseList.value = res.map(item => {
    return {
      ...item,
      formatOpenTime: getFormatTime(item.openTime)
    }
  })
})

onMounted(() => {
  // 设置案由数量统计图
  getCaseReasonNums().then(res => {
    const reasonNumsElem    = echarts.init(document.getElementById('case-analysis-reason-nums') as HTMLElement);
    const reasonNumsOptions = {
      xAxis: {
        type: 'category',
        data: ['刑事案由', '民事案由', '执行案由', '国家赔偿案由', '行政案由'],
        name: '案由'
      },
      yAxis: {
        type: 'value',
        name: '数量'
      },
      series: [{
        data: res.sort((a, b) => a.caseReason - b.caseReason).map(item => item.num),
        type: 'bar'
      }]
    }
    reasonNumsElem.setOption(reasonNumsOptions);
  })

  // 设置案件类型数量统计图
  getCaseTypeNums().then(res => {
    const typeNumsElem    = echarts.init(document.getElementById('case-analysis-type-nums') as HTMLElement);
    const typeNumsOptions = {
      xAxis: {
        type: 'category',
        data: ['管辖', '刑事', '民事', '行政', '国家赔偿与司法救助', '区际司法援助', '国际司法援助', '司法制裁', '非诉保全审查', '执行', '强制清算与破产', '其他'],
        name: '案件类型'
      },
      yAxis: {
        type: 'value',
        name: '数量'
      },
      series: [{
        data: res.sort((a, b) => a.caseType - b.caseType).map(item => item.num),
        type: 'bar'
      }]
    }
    typeNumsElem.setOption(typeNumsOptions);
  })

  // 设置案件状态数量统计图
  getCaseStatusNums().then(res => {
    const statusNumsElem    = echarts.init(document.getElementById('case-analysis-status-nums') as HTMLElement);
    const statusNumsOptions = {
      tooltip: {
        trigger: 'item'
      },
      legend: {
        top: '5%',
        left: 'center'
      },
      series: [
        {
          name: 'Access From',
          type: 'pie',
          radius: ['40%', '70%'],
          avoidLabelOverlap: false,
          itemStyle: {
            borderRadius: 10,
            borderColor: '#fff',
            borderWidth: 2
          },
          label: {
            show: false,
            position: 'center'
          },
          data: [
            { value: res[0].num, name: '未开始' },
            { value: res[1].num, name: '进行中' },
            { value: res[2].num, name: '已终止' },
            { value: res[3].num, name: '已结束' },
            { value: res[4].num, name: '已删除' }
          ]
        }
      ]
    }
    statusNumsElem.setOption(statusNumsOptions);
  })
})
</script>

<template>
  <div id="case-analysis">
    <el-row justify="space-evenly">
      <el-col :span="11">
        <div class="card" style="width: 100%">
          <div class="card-title">案由数量统计图</div>
          <div id="case-analysis-reason-nums" style="width: 100%; height: 480px"/>
        </div>
      </el-col>
      <el-col :span="11">
        <div class="card" style="width: 100%">
          <div class="card-title">案件状态数量统计图</div>
          <div id="case-analysis-status-nums" style="width: 100%; height: 480px"/>
        </div>
      </el-col>
    </el-row>

    <div class="card" style="width: 100%; margin-top: 32px">
      <div class="card-title">案件类型数量统计图</div>
      <div id="case-analysis-type-nums" style="width: 100%; height: 480px"/>
    </div>

    <div id="case-analysis-list-box" class="card">
      <el-table :data="caseList" stripe>
        <el-table-column prop="caseName" label="案件名称" width="240"/>
        <el-table-column prop="caseDescription" label="案情概要" width="540"/>
        <el-table-column prop="caseReason" label="案由" width="360"/>
        <el-table-column prop="formatOpenTime" label="最近打开时间" width="180"/>
        <el-table-column label="操作">
          <template #default>
            <el-button plain>查看</el-button>
            <el-button type="primary" plain>分析</el-button>
            <el-button type="danger" plain>删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script lang="ts">
export default {
  name: "case-analysis-page"
}
</script>

<style scoped>
#case-analysis {
  width: 100%;
  padding-bottom: 120px;
}

#case-analysis-list-box {
  width: 100%;
  margin-top: 32px;
}
</style>
