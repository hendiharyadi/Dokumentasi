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
                icon: 'success',
                title: 'Country has been created!',
                showConfirmButton: false,
                timer: 1500
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
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes, update it!'
    }).then((result) => {
        if (result.isConfirmed) {
            $.ajax({
                method: "PUT",
                url: "/api/country/" + idVal,
                dataType: "JSON",
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
                        icon: 'success',
                        title: 'Country has been updated!',
                        showConfirmButton: false,
                        timer: 1500
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
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes, delete it!'
    }).then((result) => {
        if (result.isConfirmed) {
            $.ajax({
                method: "DELETE",
                url: "/api/country/" + id,
                dataType: "JSON",
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
                        backdrop: `
                        rgba(0,0,123,0.4)
                        url("https://sweetalert2.github.io/images/nyan-cat.gif")
                        left top
                        no-repeat
                        `
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
        success: function(res){
            console.log(res);
            for(var i = 0; i < res.length; i++){
                s+= '<option value ="'+ res[i].id + '">' + res[i].name + '</option>';
            }
            $('#update-country-region').html(s);
        }
    })
}