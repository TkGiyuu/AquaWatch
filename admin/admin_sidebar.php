<?php $cur = basename($_SERVER['PHP_SELF'],'.php'); ?>
<aside class="admin-sidebar" id="adminSidebar">
  <nav class="admin-nav">
    <p class="admin-nav-section">Main</p>
    <a href="index.php" class="admin-nav-link <?= $cur==='index'?'active':'' ?>">
      <i class="bi bi-speedometer2"></i><span>Dashboard</span>
    </a>
    <p class="admin-nav-section mt-3">Management</p>
    <a href="users.php" class="admin-nav-link <?= $cur==='users'||$cur==='user_view'?'active':'' ?>">
      <i class="bi bi-people-fill"></i><span>Users</span>
    </a>
    <a href="orders.php" class="admin-nav-link <?= $cur==='orders'?'active':'' ?>">
      <i class="bi bi-receipt"></i><span>Orders</span>
    </a>
    <p class="admin-nav-section mt-3">Account</p>
    <a href="../homepage.php" target="_blank" class="admin-nav-link">
      <i class="bi bi-box-arrow-up-right"></i><span>View Site</span>
    </a>
    <a href="logout.php" class="admin-nav-link admin-nav-logout">
      <i class="bi bi-box-arrow-right"></i><span>Logout</span>
    </a>
  </nav>
</aside>
<script>
function toggleSidebar(){
  document.getElementById('adminSidebar').classList.toggle('open');
  document.getElementById('sidebarBackdrop').classList.toggle('show');
}
</script>
