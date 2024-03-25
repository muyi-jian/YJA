import instance from "@/utils/request";

export function test(){
  return instance.request({
    url: '/getUniversity',
    method: 'POST'
  })
}
