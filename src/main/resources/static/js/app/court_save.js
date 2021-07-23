var main = {
    init : function () {
        var _this = this;
        $('#btn-court-save').on('click', function () {
            _this.save();
        });
        $('#btn-court-delete').on('click',function (){
            _this.delete();
        })

    },
    save : function () {
        var data = {
            name: $('#inputCourtName').val(),
            e : $('#inputCourtE').val(),
            n : $('#inputCourtN').val(),
            address : $('#inputCourtAddress').val(),
            web : $('#inputCourtWeb').val(),
            info : $('#inputCourtInfo').val(),
            district : $('#inputCourtDistrict').val()
        };
        $.ajax({
            type: 'POST',
            url: '/api/v2/court',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('코트가 등록되었습니다.');
            window.location.href = '/admin/court';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    delete : function () {
        var id = $('#id').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/v2/court/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8'
        }).done(function() {
            alert('해당 코트가 삭제되었습니다.');
            window.location.href = '/admin/court';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }

};

main.init();