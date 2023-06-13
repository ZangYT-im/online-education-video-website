import request from '@/utils/request'

export default {
  //1 根据课程id获取章节和小节列表
  getAllChapterVideo(courseId) {
    return request({
      url: `/eduservice/chapter/getChapterVideo/${courseId}`,
      method: 'get',
    })
  },
  //添加小节
  addVideo(video) {
    return request({
      url: `/eduservice/video/addVideo`,
      method: 'post',
      data:video
    })
  },

  //根据id查询小节
  getChapter(chapterId) {
    return request({
      url: `/eduservice/chapter/getChapterInfo/${chapterId}`,
      method: 'get',
    })
  },
  //修改章节
  updateChapter(chapter) {
    return request({
      url: `/eduservice/chapter/updateChapter`,
      method: 'post',
      data:chapter
    })
  },
  //删除章节
  deleteVideo(id) {
    return request({
      url: `/eduservice/video/${id}`,
      method: 'delete'
    })
  },
  //删除阿里云视频
  deleteAliyunVod(id) {
    return request({
      url: `/eduvod/video/removeAliyunVideo/${id}`,
      method: 'delete'
    })
  },

}

