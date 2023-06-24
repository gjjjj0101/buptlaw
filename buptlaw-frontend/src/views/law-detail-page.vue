<script setup lang="ts">
import { Law }            from "../types/law";
import { ref } from "vue";
import { getLawById }     from "../services/law";
import { getFormatLawStatus, getFormatLawStatusColor, getFormatDate } from "../utils/utils";

const props = defineProps<{ id: string }>()
const law   = ref<Law>({
  expiry: 0,
  publish: 0,
  office: [''],
  status: '',
  level: '',
  html: '',
  title: '',
  pkey: '',
  id: '',
  p_id: '',
  law_class_1: '',
  law_class_2: '',
  office_class_1: '',
  office_class_2: '',
})

getLawById(props.id).then(res => {
  law.value = res
})

</script>

<template>
  <div id="law-detail">
    <div id="law-detail-info-box" class="card">
      <el-descriptions title="法律法规详细信息" border column="2">
        <el-descriptions-item label="法律效力位阶">{{ law.level }}</el-descriptions-item>
        <el-descriptions-item label="指定机关">{{ law.office ? law.office[0] : '未知' }}</el-descriptions-item>
        <el-descriptions-item label="公布日期">{{ law.publish ? getFormatDate(law.publish) : '未知' }}</el-descriptions-item>
        <el-descriptions-item label="执行日期">{{ law.expiry ? getFormatDate(law.expiry) : '未知' }}</el-descriptions-item>
        <el-descriptions-item label="时效性">
          <el-tag :color="getFormatLawStatusColor(law.status)" style="color: #ffffff; border: none">
            {{ getFormatLawStatus(law.status) }}
          </el-tag>
        </el-descriptions-item>
      </el-descriptions>
    </div>

    <div id="law-detail-content-box" class="card">
      <div style="text-align: center">
        <h1>{{ law.title }}</h1>
      </div>
      <div v-html="law.html"/>
    </div>
  </div>
</template>

<script lang="ts">
export default {
  name: "law-detail-page"
}
</script>

<style>
#law-detail {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding-bottom: 64px;
}

#law-detail p {
  margin: 1em 0;
  line-height: 1.8em;
}

#law-detail blockquote {
  margin: 1em 0 1em 1.7em;
  padding-left: 1em;
  border-left: 2px solid #e6e6e6;
  color: #606060;
}

#law-detail-info-box,
#law-detail-content-box {
  width: 960px;
  margin-top: 32px;
  padding: 24px 48px;
  box-sizing: border-box;
}
</style>
