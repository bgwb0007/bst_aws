var main = {
    init : function () {
        var _this = this;
        $('#btn-comment-save').on('click', function () {
            _this.save();
        });

        $('#btn-comment-update').on('click', function () {
            _this.update();
        });

    },
    save : function () {
        var postId = $('#postId').val().toString();
        var data = {
            content: $('#inputInfo').val(),
            status : 'on',
            postId : $('#postId').val(),
            userId : $('#commentWriter').val()
        };
        $.ajax({
            type: 'POST',
            url: '/api/v2/comment',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('댓글이 등록되었습니다.');
            window.location.href = '/post/'+postId;
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    update : function () {
        var data = {
            title: $('#title').val(),
            content: $('#content').val()
        };

        var id = $('#id').val();

        $.ajax({
            type: 'PUT',
            url: '/api/v1/posts/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 수정되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },


};

main.init();