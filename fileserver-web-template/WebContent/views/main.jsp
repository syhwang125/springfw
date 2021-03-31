<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<%@ include file="../header/header.jspf" %>

<main id="main" class="container-wrap">
    <div class="container">

        <h2 class="page-title text-center">File Server</h2>

        <div class="content">
            <div class="search-form">
                <form action="">
                    <div class="form-group">
                        <div class="input-group">
                            <input type="text" class="form-control" id="name-like" placeholder="검색어를 입력하세요.">
                            <div class="input-group-btn">
                                <button type="button" class="btn btn-primary" onclick="findNameLike()"><span
                                        class="glyphicon glyphicon-search"></span></button>
                            </div>
                        </div>
                    </div>
                </form>
            </div>

            <div class="row btn-action">
                <div class="col-sm-12">
                    <div class="fl-right">
                        <button class="btn btn-default" data-toggle="modal" data-target="#myModal">
                            <i class="fas fa-plus"></i>
                            업로드
                        </button>
                        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
                             aria-hidden="true" data-backdrop="static" >
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" onclick="resetFileList()"
                                                aria-label="Close"><span
                                                aria-hidden="true">&times;</span></button>
                                        <h4 class="modal-title" id="myModalLabel">업로드</h4>
                                    </div>
                                    <div class="modal-body">
                                        <form id="upload-file-form" method="POST" action="/rest/upload" enctype="multipart/form-data" >
                                            <label for="upload-file" style="border-color: #15171a; flex-flow: row-reverse;">
                                                파일 선택 <span class="glyphicon glyphicon-folder-open"></span>
                                            </label>
                                            <input style="display: none" type="file" id="upload-file" name="file" multiple>
                                        </form>
                                        <div class="table-wrap">
                                            <table class="table text-center">
                                                <thead>
                                                <tr>
                                                    <th class="text-center">No</th>
                                                    <th class="text-center">파일명</th>
                                                    <th class="text-center">크기</th>
                                                </tr>
                                                </thead>
                                                <tbody id="file-list">
                                                </tbody>
                                            </table>
                                        </div>
                                        <div class="row btn-action">
                                            <div class="col-sm-12">
                                                <div class="fl-right">
                                                    <button onclick="upload()" class="btn btn-primary btn-bordered">
                                                        <i class="fas fa-check"></i> 업로드
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="table-wrap">
                <table class="table text-center">
                    <thead>
                    <tr>
                        <th class="text-center">No</th>
                        <th class="text-center">파일명</th>
                        <th class="text-center">다운로드</th>
                        <th class="text-center">삭제</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="fileName" items="${fileNames}" varStatus="status">
                        <tr>
                            <th scope="row" class="text-center">${status.index + 1}</th>
                            <td>${fileName}</td>
                            <td><button class="glyphicon glyphicon-download" aria-hidden="true" onclick="download('${fileName}')" ></button></td>
                            <td><button class="glyphicon glyphicon-trash" aria-hidden="true" onclick="remove('${fileName}')" ></button></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</main>

<script>

	const nameLike = document.getElementById("name-like");
	function findNameLike() {
		window.location.href = "/file/find?nameLike=" + nameLike.value;
	}
	
    function fileDescList(files) {
        let inputHtml = '';
        let idx = 0;
        for(const file of files){
            inputHtml += "<tr>";
            inputHtml += "<th scope='row' class='text-center'>"+ (idx+1) + '</th>';
            inputHtml += "<td>" + file.name + "</td>";
            inputHtml += "<td>" + file.size/1024 + "KB</td>";
            inputHtml += "</tr>";
            idx++;
        }
        document.getElementById("file-list").innerHTML = inputHtml;
    }

    function resetFileList() {
        document.getElementById("file-list").innerHTML = '';
        fileChooser.value = '';
    }

    const fileChooser = document.getElementById("upload-file");
    fileChooser.addEventListener("change", function () {
        fileDescList(this.files);
    })

    const formData = document.getElementById("upload-file-form");
    function upload() {
    	formData.submit();
    }
    
    function download(fileName) {
    	window.location.href='/rest/download?fileName=' + fileName;
    }

    
    function remove(fileName) {
    	if(confirm('정말 삭제하시겠습니까?')) {
    		axios.get('/rest/remove?fileName='+fileName)
            .then(() => window.location.reload());	
    	}
    }

</script>

<%@ include file="../footer/footer.jspf" %>

</html>



