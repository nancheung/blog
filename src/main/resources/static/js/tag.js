
/*
 *   Copyright 2016-2017. the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

/*初始化文章分页信息*/
$(function () {
    var tagId = $("#main").data("id");
    $('body').addClass('loaded');
    $.ajax({
        type: 'GET',
        url: '/pager/tags/'+tagId,
        data:pager,
        success: function (data){
            pager = data;
            $("#pagination").data("type","tags");
            // 由于插件在没有数据的时候会报错，所以添加一层判断
            if (pager.totalCount > 0){
                initPage(tagId);
            }
        }
    });

})




