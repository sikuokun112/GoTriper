(function() {

    var db = {

        loadData: function(filter) {
            return $.grep(this.clients, function(client) {
                return (!filter.vandeid || client.vandeid.indexOf(filter.vandeid) > -1)
                    && (filter.vandename === undefined || client.vandename === filter.vandename) && (filter.noidung === undefined || client.noidung === filter.noidung)&& (filter.usercreateid === undefined || client.usercreateid === filter.usercreateid)&& (filter.tinhtrang === undefined || client.tinhtrang === filter.tinhtrang);
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