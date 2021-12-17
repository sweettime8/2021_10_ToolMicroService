export const menuItems = [
    {
        id: 1,
        label: 'menuitems.home.text',
        icon: 'bx-home-circle',
        link: '/'
    },
    {
        id: 25,
        label: 'menuitems.apps.text',
        icon: 'bx-customize',
        subItems: [
            {
                id: 26,
                label: 'menuitems.drawdiagram.text',
                link: '/diagram',
                parentId: 25
            },
                        
            {
                id: 31,
                label: 'menuitems.calendar.text',
                link: '/calendar',
                parentId: 25
            },           

            {
                id: 58,
                label: 'menuitems.tasks.text',
                subItems: [
                    {
                        id: 59,
                        label: 'menuitems.tasks.list.tasklist',
                        link: '/tasks/list',
                        parentId: 58
                    },
                    {
                        id: 60,
                        label: 'menuitems.tasks.list.kanban',
                        link: '/tasks/kanban',
                        parentId: 58
                    },
                    {
                        id: 61,
                        label: 'menuitems.tasks.list.createtask',
                        link: '/tasks/create',
                        parentId: 58
                    }
                ]
            },
            {
                id: 62,
                label: 'menuitems.contacts.text',
                icon: 'bxs-user-detail',
                subItems: [
                    {
                        id: 63,
                        label: 'menuitems.contacts.list.usergrid',
                        link: '/contacts/grid',
                        parentId: 62
                    },
                    {
                        id: 64,
                        label: 'menuitems.contacts.list.userlist',
                        link: '/contacts/list',
                        parentId: 61
                    },
                    {
                        id: 65,
                        label: 'menuitems.contacts.list.profile',
                        link: '/contacts/profile',
                        parentId: 61
                    }
                ]
            }           
        ]
    },
    {
        id: 70,
        icon: 'bx-collection',
        label: 'menuitems.programings.text',
        subItems: [
            {
                id: 71,
                label: 'menuitems.java.text',
                subItems: [
                    {
                        id: 72,
                        label: 'menuitems.java.list.javaBasic',
                        link: '/form/javaBasic',
                        parentId: 71
                    },
                    {
                        id: 73,
                        label: 'menuitems.java.list.javaAdvance',
                        link: '/form/javaAdvance',
                        parentId: 71
                    },
                    {
                        id: 74,
                        label: 'menuitems.java.list.springFramework',
                        link: '/form/springFramework',
                        parentId: 71
                    },                    
                ]
            },
            {
                id: 81,
                label: 'menuitems.tables.text',
                subItems: [
                    {
                        id: 82,
                        label: 'menuitems.tables.list.basic',
                        link: '/tables/basic',
                        parentId: 81
                    },
                    {
                        id: 83,
                        label: 'menuitems.tables.list.advanced',
                        link: '/tables/advanced',
                        parentId: 81
                    }
                ]
            },
            {
                id: 84,
                label: 'menuitems.charts.text',
                subItems: [
                    {
                        id: 85,
                        label: 'menuitems.charts.list.apex',
                        link: '/charts/apex',
                        parentId: 84
                    },
                    {
                        id: 86,
                        label: 'menuitems.charts.list.chartjs',
                        link: '/charts/chartjs',
                        parentId: 84
                    },
                    {
                        id: 87,
                        label: 'menuitems.charts.list.chartist',
                        link: '/charts/chartist',
                        parentId: 84
                    },
                    {
                        id: 88,
                        label: 'menuitems.charts.list.echart',
                        link: '/charts/echart',
                        parentId: 84
                    }
                ]
            },
            {
                id: 89,
                label: 'menuitems.icons.text',
                subItems: [
                    {
                        id: 90,
                        label: 'menuitems.icons.list.boxicons',
                        link: '/icons/boxicons',
                        parentId: 89
                    },
                    {
                        id: 91,
                        label: 'menuitems.icons.list.materialdesign',
                        link: '/icons/materialdesign',
                        parentId: 89
                    },
                    {
                        id: 92,
                        label: 'menuitems.icons.list.dripicons',
                        link: '/icons/dripicons',
                        parentId: 89
                    },
                    {
                        id: 93,
                        label: 'menuitems.icons.list.fontawesome',
                        link: '/icons/fontawesome',
                        parentId: 89
                    },
                ]
            },
            {
                id: 94,
                label: 'menuitems.maps.text',
                subItems: [
                    {
                        id: 95,
                        label: 'menuitems.maps.list.googlemap',
                        link: '/maps/google',
                        parentId: 94
                    },
                    {
                        id: 96,
                        label: "menuitems.maps.list.leafletmap",
                        link: "/maps/leaflet",
                        parentId: 94
                    },
                ]
            }
        ]
    },
    {
        id: 97,
        label: 'navbar.dropdown.megamenu.extrapages.title',
        icon: 'bx-file',
        subItems: [
            {
                id: 98,
                label: 'menuitems.invoices.text',
                subItems: [
                    {
                        id: 99,
                        label: 'menuitems.invoices.list.invoicelist',
                        link: '/invoices/list',
                        parentId: 98
                    },
                    {
                        id: 100,
                        label: 'menuitems.invoices.list.invoicedetail',
                        link: '/invoices/detail',
                        parentId: 98
                    },
                ]
            },
            {
                id: 101,
                label: 'menuitems.authentication.text',
                subItems: [
                    {
                        id: 102,
                        label: "menuitems.authentication.list.login",
                        link: "/auth/login-1",
                        parentId: 101
                    },
                    {
                        id: 103,
                        label: "menuitems.authentication.list.login-2",
                        link: "/auth/login-2",
                        parentId: 101
                    },
                    {
                        id: 104,
                        label: "menuitems.authentication.list.register",
                        link: "/auth/register-1",
                        parentId: 101
                    },
                    {
                        id: 105,
                        label: "menuitems.authentication.list.register-2",
                        link: "/auth/register-2",
                        parentId: 101
                    },
                    {
                        id: 106,
                        label: "menuitems.authentication.list.recoverpwd",
                        link: "/auth/recoverpwd",
                        parentId: 101
                    },
                    {
                        id: 107,
                        label: "menuitems.authentication.list.recoverpwd-2",
                        link: "/auth/recoverpwd-2",
                        parentId: 101
                    },
                    {
                        id: 108,
                        label: "menuitems.authentication.list.lockscreen",
                        link: "/auth/lock-screen",
                        parentId: 101
                    },
                    {
                        id:109,
                        label: "menuitems.authentication.list.lockscreen-2",
                        link: "/auth/lock-screen-2",
                        parentId: 101
                    },
                    {
                        id: 110,
                        label: "menuitems.authentication.list.confirm-mail",
                        link: "/auth/comfirm-mail",
                        parentId: 101
                    },
                    {
                        id: 111,
                        label: "menuitems.authentication.list.confirm-mail-2",
                        link: "/auth/comfirm-mail-2",
                        parentId: 101
                    },
                    {
                        id: 112,
                        label: "menuitems.authentication.list.verification",
                        link: "/auth/email-verification",
                        parentId: 101
                    },
                    {
                        id: 113,
                        label: "menuitems.authentication.list.verification-2",
                        link: "/auth/email-verification-2",
                        parentId: 101
                    },
                    {
                        id: 114,
                        label: "menuitems.authentication.list.verification-step",
                        link: "/auth/two-step-verification",
                        parentId: 101
                    },
                    {
                        id: 115,
                        label: "menuitems.authentication.list.verification-step-2",
                        link: "/auth/two-step-verification-2",
                        parentId: 101
                    }
                ]
            },
            {
                id: 116,
                label: 'menuitems.utility.text',
                icon: 'bx-file',
                subItems: [
                    {
                        id: 117,
                        label: 'menuitems.utility.list.starter',
                        link: '/pages/starter',
                        parentId: 116
                    },
                    {
                        id: 118,
                        label: 'menuitems.utility.list.maintenance',
                        link: '/pages/maintenance',
                        parentId: 116
                    },
                    {
                        id: 118,
                        label: "menuitems.utility.list.comingsoon",
                        link: "/pages/coming-soon",
                        parentId: 116
                    },
                    {
                        id: 119,
                        label: 'menuitems.utility.list.timeline',
                        link: '/pages/timeline',
                        parentId: 116
                    },
                    {
                        id: 120,
                        label: 'menuitems.utility.list.faqs',
                        link: '/pages/faqs',
                        parentId: 116
                    },
                    {
                        id: 121,
                        label: 'menuitems.utility.list.pricing',
                        link: '/pages/pricing',
                        parentId: 116
                    },
                    {
                        id: 122,
                        label: 'menuitems.utility.list.error404',
                        link: '/pages/404',
                        parentId: 116
                    },
                    {
                        id: 123,
                        label: 'menuitems.utility.list.error500',
                        link: '/pages/500',
                        parentId: 116
                    },
                ]
            },
        ]
    }
];

