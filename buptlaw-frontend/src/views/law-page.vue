<script setup lang="ts">
import { onMounted, ref }                                             from "vue";
import { getLawBySearch }                                             from "../services/law";
import { Law, LawSearchBody }                                         from "../types/law";
import { LAW_CLASS_TREE_SELECT_LIST, OFFICE_CLASS_TREE_SELECT_LIST }  from "../consts/law";
import { getFormatDate, getFormatLawStatus, getFormatLawStatusColor } from "../utils/utils";

// 搜索查询的参数
const lawSearchBody = ref<LawSearchBody>({
  content: "",
  title: true,
  isExactly: false,
} as LawSearchBody)

// 搜索查询的时间范围参数，用于双向绑定
// 实际使用时，需要将时间范围参数转换为时间戳后存入 lawSearchBody
const publishTimeRange = ref<(number | undefined)[]>([])
const expiryTimeRange  = ref<(number | undefined)[]>([])

// 切换高级搜索的显示
const switchLawSearchConfiguration = () => {
  const lawSearchConfiguration = document.getElementById("law-search-configuration")
  if (lawSearchConfiguration.style.display === "none" || lawSearchConfiguration.style.display === "") {
    lawSearchConfiguration.style.display = "block"
  } else {
    lawSearchConfiguration.style.display = "none"
  }
}

// 选择法律类型时，如果选择了大类，则去掉修改、废止的决定
const handleLawClassCheckBoxClick = (data: any) => {
  if (data.value === '0200') {
    // 去掉修改、废止的决定（0211）
    lawSearchBody.value.lawClass = lawSearchBody.value.lawClass.filter(((item: string) => item !== '0211') as any)
  } else if (data.value === '0500') {
    // 去掉修改、废止的决定（0510）
    lawSearchBody.value.lawClass = lawSearchBody.value.lawClass.filter(((item: string) => item !== '0510') as any)
  }
}

// 当时间范围发生变化时，将时间范围转换为时间戳存入 lawSearchBody
const timeOnChange = (type: string) => {
  if (type === 'publish') {
    if (publishTimeRange.value[0]) {
      lawSearchBody.value.publishStart = (publishTimeRange.value[0] as Date).getTime()
    }
    if (publishTimeRange.value[1]) {
      lawSearchBody.value.publishEnd = (publishTimeRange.value[1] as Date).getTime()
    }
  } else if (type === 'expiry') {
    if (expiryTimeRange.value[0]) {
      lawSearchBody.value.expiryStart = (expiryTimeRange.value[0] as Date).getTime()
    }
    if (expiryTimeRange.value[1]) {
      lawSearchBody.value.expiryEnd = (expiryTimeRange.value[1] as Date).getTime()
    }
  }
}

// searchResult 为继承了 Law 的对象数组引用
const searchResult = ref<(Law & {
  formatPublishTime: string,  // 格式化后的发布时间
  formatStatus: string,       // 格式化后的法律状态
})[]>([])

// 点击搜索按钮时，发送搜索请求
const onSearch = () => {
  getLawBySearch(lawSearchBody.value, 1, 10).then(res => {
    searchResult.value = (res.searchHits).map(i => ({
      ...(i.content),
      formatPublishTime: i.content.publish ? getFormatDate(i.content.publish) : "未知",
      formatStatus: i.content.status ? getFormatLawStatus(i.content.status) : "未知",
    }))
    console.log(res)
  })
}

// onMounted(() => {
//   getLawBySearch(lawSearchBody.value, 1, 10).then(res => {
//     console.log(res)
//   })
// })
</script>

<template>
  <div id="law">
    <div id="law-search-box">
      <input id="law-search-input" v-model="lawSearchBody.content"/>
      <button id="law-search-button" @click="onSearch">
        <font-awesome-icon icon="magnifying-glass"/>
      </button>

      <button id="law-search-configuration-button" @click="switchLawSearchConfiguration">
        高级搜索
        <font-awesome-icon icon="caret-down" style="margin-left: 8px"/>
      </button>

      <div id="law-search-configuration">
        <el-row>
          <el-col :span="11">
            <el-form-item label="查找方式">
              <el-radio-group v-model="lawSearchBody.isExactly">
                <el-radio :label="true">精确查找</el-radio>
                <el-radio :label="false">模糊查找</el-radio>
              </el-radio-group>
            </el-form-item>
            <el-form-item label="检索范围">
              <el-checkbox :label="1" v-model="lawSearchBody.title">标题</el-checkbox>
              <el-checkbox :label="2" v-model="lawSearchBody.html">正文</el-checkbox>
            </el-form-item>
            <el-form-item label="发布日期">
              <el-date-picker
                  v-model="publishTimeRange"
                  type="daterange"
                  range-separator="至"
                  start-placeholder="起始时间"
                  end-placeholder="终止时间"
                  unlink-panels
                  @change="timeOnChange('publish')"
              />
            </el-form-item>
            <el-form-item label="执行日期">
              <el-date-picker
                  v-model="expiryTimeRange"
                  type="daterange"
                  range-separator="至"
                  start-placeholder="起始时间"
                  end-placeholder="终止时间"
                  unlink-panels
                  @change="timeOnChange('expiry')"
              />
            </el-form-item>
          </el-col>
          <el-col :span="11" :offset="2">
            <el-form-item label="时效性">
              <el-checkbox-group v-model="lawSearchBody.status">
                <el-checkbox label="1">已生效</el-checkbox>
                <el-checkbox label="3">未生效</el-checkbox>
                <el-checkbox label="5">已修改</el-checkbox>
                <el-checkbox label="7">无时效</el-checkbox>
                <el-checkbox label="9">已废止</el-checkbox>
              </el-checkbox-group>
            </el-form-item>
            <el-form-item label="法规类型" style="margin-top: 36px">
              <el-tree-select
                  v-model="lawSearchBody.lawClass"
                  :data="LAW_CLASS_TREE_SELECT_LIST"
                  multiple
                  show-checkbox
                  @check="handleLawClassCheckBoxClick"
                  clearable
                  collapse-tags
                  placeholder="请选择法规类型"
              />
            </el-form-item>
            <el-form-item label="指定机关">
              <el-tree-select
                  v-model="lawSearchBody.officeClass"
                  :data="OFFICE_CLASS_TREE_SELECT_LIST"
                  multiple
                  show-checkbox
                  clearable
                  collapse-tags
                  placeholder="请选择指定机关"
              />
            </el-form-item>
          </el-col>
        </el-row>
      </div>
    </div>

    <div id="law-search-result-box" class="card" v-if="searchResult.length">
      <el-table :data="searchResult" stripe>
        <el-table-column type="index" label="序号" width="80"/>
        <el-table-column prop="title" label="标题" width="240"/>
        <el-table-column prop="office" label="制定机关" width="360"/>
        <el-table-column prop="level" label="法律性质" width="240"/>
        <el-table-column prop="formatStatus" label="时效性" width="180">
          <template #default="scope">
            <el-tag :color="getFormatLawStatusColor(scope.row.status)" style="color: #ffffff; border: none">
              {{ scope.row.formatStatus }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="formatPublishTime" label="发布日期"/>
      </el-table>
    </div>

  </div>
</template>

<script lang="ts">
export default {
  name: "main-page"
}
</script>

<style scoped>
#law {
  padding: 0 240px;
}

#law-search-box {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-top: 36px;
}

#law-search-input {
  width: 640px;
  height: 64px;
  border-radius: 12px 0 0 12px;
  border: 1px solid #ebebeb;
  border-right: none;
  padding: 0 16px;
  font-size: 16px;
  color: var(--text-color);
  outline: none;
  box-sizing: border-box;
}

#law-search-button {
  width: 64px;
  height: 64px;
  border: 1px solid #ebebeb;
  border-left: none;
  border-right: none;
  font-size: 24px;
  color: #ffffff;
  background: var(--theme-color);
  box-sizing: border-box;
  display: flex;
  justify-content: center;
  align-items: center;
  cursor: pointer;
}

#law-search-button:active {
  background: var(--theme-active-color);
}

#law-search-configuration-button {
  width: 120px !important;
  height: 64px;
  border-radius: 0 12px 12px 0;
  border: 1px solid #ebebeb;
  border-left: 2px solid #ebebeb;
  font-size: 18px;
  color: #ffffff;
  background: var(--theme-color);
  box-sizing: border-box;
  display: flex;
  justify-content: center;
  align-items: center;
  cursor: pointer;
}

#law-search-configuration {
  width: 824px;
  background: #ffffff;
  padding: 24px;
  border: 1px solid var(--theme-color);
  border-radius: 12px;
  box-sizing: border-box;
  font-size: 14px;
  position: absolute;
  top: 200px;
  display: none;
  z-index: 9;
}

#law-search-configuration::before {
  content: '';
  position: absolute;
  top: -7px;
  right: 54px;
  width: 12px;
  height: 12px;
  transform: rotate(45deg);
  border: 1px solid var(--theme-color);
  border-bottom: none;
  border-right: none;
  background: #ffffff;
}

#law-search-result-box {
  width: 100%;
  margin-top: 36px;
  box-sizing: border-box;
  font-size: 14px;
}
</style>
