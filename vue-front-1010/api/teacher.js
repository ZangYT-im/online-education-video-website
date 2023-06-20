import request from '@/utils/request'

export default {
  //分页讲师查询的方法
  getTeacherList(page,limit) {
    return request({
      url: `/eduservice/teacherfront/getTeacherFront/${page}/${limit}`,
      method: 'post'
    })
  },
  //讲师详情的方法
  getTeacherInfo(teacherId) {
    return request({
      url: `/eduservice/teacherfront/getTeacherFrontInfo/${teacherId}`,
      method: 'post'
    })

  }
}
