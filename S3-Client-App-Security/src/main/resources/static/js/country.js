$('#table-country').DataTable({
    ajax: {
        url: '/api/country',
        dataSrc: ''
    },
    columns: [{
        data: 'id'
    }, {
        data: 'code'
    }, {
        data: 'name'
    }, {
        data: 'region.name'
    }, {
        data: null,
        render: function (data, type, row, meta) {
            return ` <button type="button" style = "background-color: #3D426B; color: #F8F7F3;" class="btn" data-bs-toggle="modal" data-bs-target="#detailCountry" onclick="findById(${data.id})">
                Detail
            </button>
            <button type="button" style = "background-color: #3D426B; color: #F8F7F3;" class="btn" data-bs-toggle="modal" data-bs-target="#updateCountry" onclick="beforeUpdate(${data.id})">
                Edit
            </button>
            <button class="btn" style = "color: red;" onclick="deleteCountry(${data.id})">
                Delete
            </button>
            `;
        }
    }]
})
function findById(id) {
    $.ajax({
        method: "GET",
        url: "/api/country/" + id,
        dataType: "JSON",
        success: result => {
            $("#country-id").text(`${result.id}`)
            $("#country-code").text(`${result.code}`)
            $("#country-image").attr("src","https://countryflagsapi.com/svg/"+result.name)
            $("#country-name").text(`${result.name}`)
            $("#country-region").text(`${result.region.name}`)
        }
    })
}

function create() {
    let code = $('#insert-country-code').val()
    let nameVal = $('#insert-country-name').val()
    let region = $('#insert-country-region').val()
    console.log(region);
    $.ajax({
        method: "POST",
        url: "/api/country",
        dataType: "JSON",
        beforeSend:addCsrfToken(),
        data: JSON.stringify({
            code: code,
            name: nameVal,
            regionId: region
        }),
        contentType: "application/json",
        success: result => {
            $('#createCountry').modal('hide')
            $('#table-country').DataTable().ajax.reload()
            $('#insert-country-name').val("")
            Swal.fire({
                position: 'center',
                iimageUrl: 'https://www.history.com/.image/ar_1:1%2Cc_fill%2Ccs_srgb%2Cfl_progressive%2Cq_auto:good%2Cw_1200/MTU3ODc4NjAzNzkzMzExNDU1/hith-the-birth-of-ok-175-years-ago-istock_000016716612large-2.jpg',
                imageWidth: 200,
                imageHeight: 200,
                imageAlt: 'Success',
                title: 'Country has been created!',
                showConfirmButton: false,
                timer: 1500,
                background:
                `rgba(248, 247, 243, 1)`
            })
        }
    })
}

function beforeUpdate(id) {
    $.ajax({
        method: "GET",
        url: "/api/country/" + id,
        dataType: "JSON",
        success: result => {
            $("#update-country-code").val(`${result.code}`)
            $("#update-country-name").val(`${result.name}`)
            $("#update-country-id").val(`${result.id}`)
            s = '<option value="' + result.region.id + '">' + result.region.name + '</option>';
            $("#update-country-region").html(getAllRegionUpdate());
        }
    })
}

function update() {
    let code = $('#update-country-code').val()
    let region = $('#update-country-region').val()
    let nameVal = $("#update-country-name").val()
    let idVal = $("#update-country-id").val()

    Swal.fire({
        title: 'Are you sure?',
        text: "You will change the entry.",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#d33',
        cancelButtonColor: '#3085d6',
        confirmButtonText: 'Yes, update it!',
        background:
                `rgba(248, 247, 243, 1)`
    }).then((result) => {
        if (result.isConfirmed) {
            $.ajax({
                method: "PUT",
                url: "/api/country/" + idVal,
                dataType: "JSON",
                beforeSend:addCsrfToken(),
                data: JSON.stringify({
                    code: code,
                    name: nameVal,
                    regionId: region
                }),
                contentType: "application/json",
                success: result => {
                    $('#updateCountry').modal('hide')
                    $('#table-country').DataTable().ajax.reload()
                    $('#update-country-name').val("")
                    Swal.fire({
                        position: 'center',
                        imageUrl: 'https://www.history.com/.image/ar_1:1%2Cc_fill%2Ccs_srgb%2Cfl_progressive%2Cq_auto:good%2Cw_1200/MTU3ODc4NjAzNzkzMzExNDU1/hith-the-birth-of-ok-175-years-ago-istock_000016716612large-2.jpg',
                        imageWidth: 200,
                        imageHeight: 200,
                        imageAlt: 'Success',
                        title: 'Country has been updated!',
                        showConfirmButton: false,
                        timer: 1500,
                        background:
                `rgba(248, 247, 243, 1)`
                    })
                }
            })
        }
    })
}

function deleteCountry(id) {
    Swal.fire({
        title: 'Are you sure?',
        text: "You WILL delete this!",
        imageUrl: 'https://www.reactiongifs.us/wp-content/uploads/2018/06/giphy-14.gif',
        imageWidth: 250,
        imageHeight: 200,
        imageAlt: 'Warnung',
        showCancelButton: true,
        confirmButtonColor: '#d33',
        cancelButtonColor: '#3085d6',
        confirmButtonText: 'Yes, delete it!',
        background:
                `rgba(248, 247, 243, 1)`
    }).then((result) => {
        if (result.isConfirmed) {
            $.ajax({
                method: "DELETE",
                url: "/api/country/" + id,
                dataType: "JSON",
                beforeSend:addCsrfToken(),
                success: result => {
                    $('#table-country').DataTable().ajax.reload() 
                    Swal.fire({
                        icon: 'success',
                        title: 'Country has been deleted!',
                        width: 600,
                        padding: '3em',
                        color: '#716add',
                        background: '#fff',
                        showConfirmButton: false,
                        timer: 1500,
                        background:
                `rgba(248, 247, 243, 1)`
                    })
                }
            })
        }
    })
}

function getAllRegion(){
    $.ajax({
        method: "GET",
        url: "/api/region",
        dataType: "JSON",
        beforeSend:addCsrfToken(),
        success: function(res){
            console.log(res);
            var s = '<option disabled> Select Region </option>';
            for(var i = 0; i < res.length; i++){
                s += '<option value ="'+ res[i].id + '">' + res[i].name + '</option>';
            }
            $('#insert-country-region').html(s);
        }
    })
}

function getAllRegionUpdate(){
    $.ajax({
        method: "GET",
        url: "/api/region",
        dataType: "JSON",
        beforeSend:addCsrfToken(),
        success: function(res){
            console.log(res);
            for(var i = 0; i < res.length; i++){
                s+= '<option value ="'+ res[i].id + '">' + res[i].name + '</option>';
            }
            $('#update-country-region').html(s);
        }
    })
}

$('#logout').on('click', function(){
    Swal.fire({
        title: 'Are you sure?',
        text: "You WILL out of this page",
        imageUrl: 'https://www.reactiongifs.us/wp-content/uploads/2018/06/giphy-14.gif',
        imageWidth: 250,
        imageHeight: 200,
        imageAlt: 'Warnung',
        showCancelButton: true,
        confirmButtonColor: '#d33',
        cancelButtonColor: '#3085d6',
        confirmButtonText: 'Sure',
        background:
                `rgba(248, 247, 243, 1)`
    }).then((result)=>{
        if(result.isConfirmed){
            document.logoutForm.submit()
        }
    })
});