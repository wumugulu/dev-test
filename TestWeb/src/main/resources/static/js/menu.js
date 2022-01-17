export default [{
    id: '1',
    label: 'del-配置管理',
    children: [{
        id: '1-1',
        label: '菜单1-1',
        target: '/menu_notset'
    }, {
        id: '1-2',
        label: 'z菜单1-2',
        target: '/menu_notset'
    }, {
        id: '1-3',
        label: '菜单1-3',
        children: [{
            id: '1-3-1',
            label: '内容1-3-1',
            target: '/menu_notset'
        }, {
            id: '1-3-2',
            label: '内容1-3-2',
            target: '/menu_notset'
        }]
    }]
}, {
    id: '2',
    label: '服务管理',
    target: '/menu_order'
}, {
    id: '3',
    label: '命名空间',
    children: [{
        id: '3-1',
        label: '菜单3-1',
        children: [{
            id: '3-1-1',
            label: '内容3-1-1',
            target: '/menu_notset'
        }, {
            id: '3-1-2',
            label: '内容3-1-2',
            target: '/menu_notset'
        }]
    }, {
        id: '3-2',
        label: '菜单3-2',
        children: [{
            id: '3-2-1',
            label: '内容3-2-1',
            target: '/menu_notset'
        }, {
            id: '3-2-2',
            label: '内容3-2-2',
            target: '/menu_notset'
        }]
    }, {
        id: '3-3',
        label: '菜单3-3',
        target: '/menu_notset'
    }]
}, {
    id: '4',
    label: '集群管理',
    target: '/menu_goods'
}]