(function() {

    var db = {

        loadData: function(filter) {
            return $.grep(this.clients, function(client) {
                return (!filter.username || client.username.indexOf(filter.username) > -1)
                    && (filter.userid === undefined || client.userid === filter.userid)
                    && (!filter.fullname || client.fullname.indexOf(filter.fullname) > -1)
                    && (!filter.email || client.email === filter.email)
                    && (filter.enable === undefined || client.enable === filter.enable);
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