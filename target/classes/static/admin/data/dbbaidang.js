(function() {

    var db = {

        loadData: function(filter) {
            return $.grep(this.clients, function(client) {
                return (!filter.baidangid || client.baidangid.indexOf(filter.baidangid) > -1)
                    && (filter.baidangname === undefined || client.baidangname === filter.baidangname) && (filter.danhmucid === undefined || client.danhmucid === filter.danhmucid)&& (filter.usercreateid === undefined || client.usercreateid === filter.usercreateid)&& (filter.diadiemid === undefined || client.diadiemid === filter.diadiemid)&& (filter.soluotxem === undefined || client.soluotxem === filter.soluotxem)&& (filter.soluotdanhgia === undefined || client.soluotdanhgia === filter.soluotdanhgia)&& (filter.tinhtrang === undefined || client.tinhtrang === filter.tinhtrang);
            });
        },

        insertItem: function(insertingClient) {
            this.clients.push(insertingClient);
        },

        updateItem: function(updatingClient) { },

        deleteItem: function(deletingClient) {
            var clientIndex = $.inArray(deletingClient, this.clients);
            this.clients.splice(clientIndex, 1);
        }

    };
    window.db = db;

}());