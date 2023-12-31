import request from '@/utils/request'
const api_name = '/admin/edu/course'
export default {
  //1 添加课程信息
  addCourseInfo(courseInfo) {
    return request({
      url: `/eduservice/course/addCourseInfo`,
      method: 'post',
      data:courseInfo
    })
  },
  //2 查询所有讲师
  getListTeacher(){
    return request({
      url: `/eduservice/teacher/findAll`,
      method: 'get'
    })
  },

  //根据课程id查询课程基本信息
  getCourseInfoId(courseId){
    return request({
      url: `/eduservice/course/getCourseInfo/${courseId}`,
      method: 'get'
    })
  },
  //修改课程
  updateCourseInfo(courseInfo){
    return request({
      url: `/eduservice/course/updateCourseInfo`,
      method: 'post',
      data:courseInfo
    })
  },
  //课程确认信息显示
  getPublishCourseInfo(id){
    return request({
      url: `/eduservice/course/getPublishCourseInfo/${id}`,
      method: 'get'
    })
  },
  //课程最终发布
  publishCourse(id){
    return request({
      url: `/eduservice/course/publishCourse/${id}`,
      method: 'post'
    })
  },
  //todo课程列表
  //课程最终发布
  getListCourse(){
    return request({
      url: `/eduservice/course`,
      method: 'get'
    })
  },
}
