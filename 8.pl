#Dynamic
$age = 19;
sub getAge{
    return $age;
}
sub actualAge{
    $age = 21;
    return getAge();
}
print "Actual age is ".actualAge()."\n";
#21

#Static
my $age = 19;
sub getAge1{
    return $age;
}
sub actualAge1{
    my $age = 21;
    return getAge1();
}
print "Actual age is ".actualAge1()."\n";
#19