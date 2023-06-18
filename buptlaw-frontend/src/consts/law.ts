//+-------------------------------------------------------------------------
//
//  法律法规相关常量
//
//  File:       law.ts
//
//  Directory:  buptlaw-frontend/src/consts
//
//  History:    6月-17-2023   QQK  Created
//
//--------------------------------------------------------------------------

export const LAW_CLASS_TREE_SELECT_LIST = [
  {
    value: '0100',
    label: '宪法',
  },
  {
    value: '0200',
    label: '法律',
    children: [
      {
        value: '0202',
        label: '宪法相关法',
      },
      {
        value: '0203',
        label: '民法商法',
      },
      {
        value: '0204',
        label: '行政法',
      },
      {
        value: '0205',
        label: '经济法',
      },
      {
        value: '0206',
        label: '社会法',
      },
      {
        value: '0207',
        label: '刑法',
      },
      {
        value: '0208',
        label: '诉讼与非诉讼程序法',
      },
      {
        value: '0209',
        label: '法律解释',
      },
      {
        value: '0210',
        label: '有关法律问题和重大问题的决定（部分）',
      },
      {
        value: '0211',
        label: '修改、废止的决定',
      },
    ]
  },
  {
    value: '0300',
    label: '行政法规',
  },
  {
    value: '0400',
    label: '监察法规',
  },
  {
    value: '0500',
    label: '地方性法规',
    children: [
      {
        value: '0502',
        label: '省级地方性法规',
      },
      {
        value: '0503',
        label: '设区的市地方性法规',
      },
      {
        value: '0504',
        label: '自治州自治条例',
      },
      {
        value: '0505',
        label: '自治县自治条例',
      },
      {
        value: '0506',
        label: '自治州单行条例',
      },
      {
        value: '0507',
        label: '自治县单行条例',
      },
      {
        value: '0508',
        label: '经济特区法规',
      },
      {
        value: '0509',
        label: '海南自由贸易港法规',
      },
      {
        value: '0510',
        label: '修改、废止的决定',
      }
    ]
  },
  {
    value: '0600',
    label: '司法解释',
  }
]

export const OFFICE_CLASS_TREE_SELECT_LIST = [
  {
    value: '0100',
    label: '全国人大及其常委会',
    children: [
      {
        value: '0101',
        label: '全国人大',
      },
      {
        value: '0102',
        label: '全国人大常委会',
      }
    ]
  },
  {
    value: '0200',
    label: '国务院',
  },
  {
    value: '0300',
    label: '国家监察委员会',
  },
  {
    value: '0400',
    label: '最高人民检察院',
  },
  {
    value: '0500',
    label: '地方人大及其常委会',
    children: [
      {
        value: '0501',
        label: '北京',
      },
      {
        value: '0502',
        label: '天津',
      },
      {
        value: '0503',
        label: '河北',
      },
      {
        value: '0504',
        label: '山西',
      },
      {
        value: '0505',
        label: '内蒙古',
      },
      {
        value: '0506',
        label: '辽宁',
      },
      {
        value: '0507',
        label: '吉林',
      },
      {
        value: '0508',
        label: '黑龙江',
      },
      {
        value: '0509',
        label: '上海',
      },
      {
        value: '0510',
        label: '江苏',
      },
      {
        value: '0511',
        label: '浙江',
      },
      {
        value: '0512',
        label: '安徽',
      },
      {
        value: '0513',
        label: '福建',
      },
      {
        value: '0514',
        label: '江西',
      },
      {
        value: '0515',
        label: '山东',
      },
      {
        value: '0516',
        label: '河南',
      },
      {
        value: '0517',
        label: '湖北',
      },
      {
        value: '0518',
        label: '湖南',
      },
      {
        value: '0519',
        label: '广东',
      },
      {
        value: '0520',
        label: '广西',
      },
      {
        value: '0521',
        label: '海南',
      },
      {
        value: '0522',
        label: '重庆',
      },
      {
        value: '0523',
        label: '四川',
      },
      {
        value: '0524',
        label: '贵州',
      },
      {
        value: '0525',
        label: '云南',
      },
      {
        value: '0526',
        label: '西藏',
      },
      {
        value: '0527',
        label: '陕西',
      },
      {
        value: '0528',
        label: '甘肃',
      },
      {
        value: '0529',
        label: '青海',
      },
      {
        value: '0530',
        label: '宁夏',
      },
      {
        value: '0531',
        label: '新疆',
      },
    ]
  }
]
