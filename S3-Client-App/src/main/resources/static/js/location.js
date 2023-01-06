$('#table-location').DataTable({
    ajax: {
        url: '/api/location',
        dataSrc: ''
    },
    columns: [{
        data: 'id'
    }, {
        data: 'street_address'
    }, {
        data: 'postal_code'
    }, {
        data: 'city'
    }, {
        data: 'state_province'
    }, {
        data: 'country.name'
    }, {    
        data: null,
        render: function (data, type, row, meta) {
            return ` <button type="button" style = "background-color: #3D426B; color: #F8F7F3;" class="btn" data-bs-toggle="modal"           data-bs-target="#detailLocation" onclick="findById(${data.id})">
                Detail
            </button>
            <button type="button" style = "background-color: #3D426B; color: #F8F7F3;" class="btn" data-bs-toggle="modal"           data-bs-target="#updateLocation" onclick="beforeUpdate(${data.id})">
                Edit
            </button>
            <button class="btn" style = "color: red;" onclick="deleteLocation(${data.id})">Delete</button>
            `;
        }
    }]
})
function findById(id) {
    $.ajax({
        method: "GET",
        url: "/api/location/" + id,
        dataType: "JSON",
        success: result => {
            $("#location-id").text(`${result.id}`)
            $("#location-street_address").text(`${result.street_address}`)
            $("#location-postal_code").text(`${result.portal_code}`)
            $("#location-city").text(`${result.city}`)
            $("#location-state_province").text(`${result.state_province}`)
            $("#location-country").text(`${result.country.name}`)
        }
    })
}
function create() {
    let street_address = $('#insert-location-street_address').val()
    let postal_code = $('#insert-location-postal_code').val()
    let city = $('#insert-location-city').val()
    let state_province = $('#insert-location-state_province').val()
    let country = $('#insert-location-country').val()

    $.ajax({
        method: "POST",
        url: "/api/location",
        dataType: "JSON",
        data: JSON.stringify({
            street_address: street_address,
            postal_code: postal_code,
            city: city,
            state_province: state_province,
            countryId: country
        }),
        contentType: "application/json",
        success: result => {
            $('#createLocation').modal('hide')
            $('#table-location').DataTable().ajax.reload()
            $('#insert-location-street_address').val("")
            $('#insert-location-postal_code').val("")
            $('#insert-location-city').val("")
            $('#insert-location-state_province').val("")
            $('#insert-location-country').val("0")
            Swal.fire({
                position: 'center',
                icon: 'success',
                title: 'Location has been created!',
                showConfirmButton: false,
                timer: 1500
            })
        }
    })
}

function beforeUpdate(id) {
    $.ajax({
        method: "GET",
        url: "/api/location/" + id,
        dataType: "JSON",
        success: result => {
            // $("#location-id").text(`${result.id}`)
            $('#update-location-street_address').val(`${result.street_address}`)
            $('#update-location-postal_code').val(`${result.postal_code}`)
            $('#update-location-city').val(`${result.city}`)
            $('#update-location-state_province').val(`${result.state_province}`)
            $("#update-location-id").val(`${result.id}`);
            s = '<option value="' + result.country.id + '">' + result.country.name + '</option>'
            $('#update-location-country').html(getAllCountryUpdate())
        }
    })
}


function update() {
    let street_address = $('#update-location-street_address').val()
    let postal_code = $('#update-location-postal_code').val()
    let city = $('#update-location-city').val()
    let state_province = $('#update-location-state_province').val()
    let country = $('#update-location-country').val()
    let id = $("#update-location-id").val()

    Swal.fire({
        title: 'Are you sure?',
        text: "You won't change location name!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes, update it!'
    }).then((result) => {
        if (result.isConfirmed) {
            $.ajax({
                method: "PUT",
                url: "/api/location/" + id,
                dataType: "JSON",
                data: JSON.stringify({
                    street_address: street_address,
                    postal_code: postal_code,
                    city: city,
                    state_province: state_province,
                    countryId: country
                }),
                contentType: "application/json",
                success: result => {
                    $('#updateLocation').modal('hide')
                    $('#table-location').DataTable().ajax.reload()
                    $('#update-location-street_address').val("")
                    // $('#update-location-postal_code').val("")
                    // $('#update-location-city').val("")
                    // $('#update-location-state_province').val("")
                    // $('#update-location-country').val("0")
                    Swal.fire({
                        position: 'center',
                        icon: 'success',
                        title: 'Location has been updated!',
                        showConfirmButton: false,
                        timer: 1500
                    })
                }
            })
        }
    })
}

function deleteLocation(id) {
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
                url: "/api/location/" + id,
                dataType: "JSON",
                success: result => {
                    $('#table-location').DataTable().ajax.reload() 
                    Swal.fire({
                        icon: 'success',
                        title: 'Location has been deleted!',
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

function getAllCountry(){
    $.ajax({
        method: "GET",
        url: "/api/country",
        dataType: "JSON",
        success: function(res){
            console.log(res);
            var s = '<option disabled> Select Country </option>';
            for(var i = 0; i < res.length; i++){
                s += '<option value ="'+ res[i].id + '">' + res[i].name + '</option>';
            }
            $('#insert-location-country').html(s);
        }
    })
}

function getAllCountryUpdate(){
    $.ajax({
        method: "GET",
        url: "/api/country",
        dataType: "JSON",
        success: function(res){
            console.log(res);
            for(var i = 0; i < res.length; i++){
                s+= '<option value ="'+ res[i].id + '">' + res[i].name + '</option>';
            }
            $('#update-location-country').html(s);
        }
    })
}