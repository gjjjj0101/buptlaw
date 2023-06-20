//+-------------------------------------------------------------------------
//
//  法律法规相关的类型定义
//
//  File:       law.ts
//
//  Directory:  buptlaw-frontend/src/types
//
//  History:    6月-15-2023   QQK  Created
//
//--------------------------------------------------------------------------

export interface Law {
  expiry: number;         // 失效时间
  publish: number;        // 发布时间
  office: string[];       // 制定机关
  status: string;         // 时效性
  level: string;          // 法律效力位阶
  html: string;           // 正文
  title: string;          // 标题
  pkey: string;           //
  id: string;             // id
  p_id: string;           //
  law_class_1: string;    // 法律效力大类代码
  law_class_2: string;    // 法律效力小类代码
  office_class_1: string; // 制定机关大类代码
  office_class_2: string; // 制定机关小类代码
}

export interface LawSearchBody {
  content?: string;       // 搜索内容
  title?: boolean;        // 是否在标题中进行搜索
  html?: boolean;         // 是否在正文中进行搜索
  isExactly?: boolean;    // 是否精确匹配
  office?: string[];      // 制定机关
  status?: string[];      // 时效性
  level?: string[];       // 法律效力位阶
  lawClass?: string[];    // 法律分类
  officeClass?: string[]; // 机构分类
  publishStart?: number;  // 发布时间范围左区间时间戳
  publishEnd?: number;    // 发布时间范围右区间时间戳
  expiryStart?: number;   // 失效时间范围左区间时间戳
  expiryEnd?: number;     // 失效时间范围右区间时间戳
}

export interface LawMixedSearchResponse {
  totalHits: number;          // 匹配查询条件总条数
  totalHitsRelation: string;
  maxScore: number;
  scrollId: string;
  searchHits: {               // 命中列表
    index: string;
    id: string;
    score: number;
    sortValues: string[];
    content: Law;
    innerHits: any;
    nestedMetaData: any;
    routing: any;
    explanation: any;
    matchedQueries: string[];
  }[],
  aggregations: any;
  suggest: any;
  empty: boolean;               // 搜索结果是否为空
}

export interface LawNormalSearchResponse {
  content: Law[];
  pageable: {
    sort: {
      empty: boolean;
      sorted: boolean;
      unsorted: boolean;
    }
    offset: number;
    pageNumber: number;
    pageSize: number;
    paged: boolean;
    unpaged: boolean;
  }
  last: boolean;
  totalPages: number;
  totalElements: number;
  first: boolean;
  size: number;
  number: number;
  sort: {
    empty: boolean;
    sorted: boolean;
    unsorted: boolean;
  }
  numberOfElements: number;
  empty: boolean;
}
