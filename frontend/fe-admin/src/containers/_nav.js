export default [
  {
    _name: 'CSidebarNav',
    _children: [
      {
        _name: 'CSidebarNavItem',
        name: 'Dashboard',
        to: '/dashboard',
        icon: 'cil-speedometer',
      },
      {
        _name: 'CSidebarNavTitle',
        _children: ['Setting']
      },
      {
        _name: 'CSidebarNavItem',
        name: 'User',
        to: '/users',
        icon: 'cil-user'
      },
      {
        _name: 'CSidebarNavItem',
        name: 'Role',
        to: '/theme/colors',
        icon: 'cil-people'
      },
      {
        _name: 'CSidebarNavDropdown',
        name: 'Quiz',
        route: '/quiz',
        icon: 'cil-calculator',
        items: [
          {
            name: 'Manager Quiz',
            to: '/quiz/manager-quiz'
          },          
          {
            name: 'Az900',
            to: '/quiz/az900'
          },
          {
            name: 'PSM1',
            to: '/quiz/psm1'
          },

        ]
      },      
    ]
  }
]