(function() {

    var db = {

        loadData: function(filter) {
            return $.grep(this.clients, function(client) {
                return (!filter.diadiemid || client.diadiemid.indexOf(filter.diadiemid) > -1)
                    && (filter.diadiemname === undefined || client.diadiemname === filter.diadiemname) && (filter.sobaidang === undefined || client.sobaidang === filter.sobaidang) && (filter.usercreateid === undefined || client.usercreateid === filter.usercreateid)&& (filter.tinhthanhid === undefined || client.tinhthanhid === filter.tinhthanhid) ;

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